package br.com.eventoadmin.bo.controleacesso;

import br.com.eventoadmin.bo.email.EmailBO;
import br.com.eventoadmin.bo.email.ModeloEmailBO;
import com.xpert.core.crud.AbstractBusinessObject;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.eventoadmin.constante.Constantes;
import br.com.eventoadmin.dao.controleacesso.SolicitacaoRecuperacaoSenhaDAO;
import br.com.eventoadmin.dao.controleacesso.UsuarioDAO;
import br.com.eventoadmin.modelo.controleacesso.SituacaoUsuario;
import br.com.eventoadmin.modelo.controleacesso.SolicitacaoRecuperacaoSenha;
import br.com.eventoadmin.modelo.email.TipoAssuntoEmail;
import br.com.eventoadmin.modelo.controleacesso.TipoRecuperacaoSenha;
import br.com.eventoadmin.modelo.controleacesso.Usuario;
import com.xpert.persistence.query.Restrictions;
import com.xpert.utils.Encryption;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author ayslan
 */
@Stateless
public class SolicitacaoRecuperacaoSenhaBO extends AbstractBusinessObject<SolicitacaoRecuperacaoSenha> {

    @EJB
    private SolicitacaoRecuperacaoSenhaDAO solicitacaoRecuperacaoSenhaDAO;
    @EJB
    private UsuarioDAO usuarioDAO;
    @EJB
    private ModeloEmailBO modeloEmailBO;
    @EJB
    private EmailBO emailBO;

    @Override
    public SolicitacaoRecuperacaoSenhaDAO getDAO() {
        return solicitacaoRecuperacaoSenhaDAO;
    }

    @Override
    public List<UniqueField> getUniqueFields() {
        return null;
    }

    @Override
    public void validate(SolicitacaoRecuperacaoSenha solicitacaoRecuperacaoSenha) throws BusinessException {
    }

    @Override
    public boolean isAudit() {
        return true;
    }

    public Date getDataValidade(Date dataCadastro) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, Constantes.MINUTOS_VALIDADE_RECUPERACAO_SENHA);
        return calendar.getTime();
    }

    public SolicitacaoRecuperacaoSenha getSolicitacaoRecuperacaoSenha(String token, String email) {

        SolicitacaoRecuperacaoSenha solicitacaoRecuperacaoSenha = solicitacaoRecuperacaoSenhaDAO.unique("token", token);
        if (solicitacaoRecuperacaoSenha != null && solicitacaoRecuperacaoSenha.getEmail().equals(email)) {
            return solicitacaoRecuperacaoSenha;
        }

        return null;
    }

    /**
     * gera um token para um SolicitacaoRecuperacaoSenha, o token eh um hash
     * SHA256 dos campos: id + string aleatoria + data atual
     *
     * @param solicitacaoRecuperacaoSenha
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String getToken(SolicitacaoRecuperacaoSenha solicitacaoRecuperacaoSenha) throws NoSuchAlgorithmException {

        //concatenar o id + string aleatoria + timestamp
        String key = solicitacaoRecuperacaoSenha.getId() + RandomStringUtils.random(20) + new Date().getTime();

        //retonar SHA256
        return Encryption.getSHA256(key);
    }

    public void enviarEmail(SolicitacaoRecuperacaoSenha solicitacaoRecuperacaoSenha, TipoRecuperacaoSenha tipoRecuperacaoSenha) throws BusinessException {

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("solicitacaoRecuperacaoSenha", solicitacaoRecuperacaoSenha);
        if (tipoRecuperacaoSenha.equals(TipoRecuperacaoSenha.ESQUECI_SENHA)) {
            emailBO.enviarAssincrono(TipoAssuntoEmail.RECUPERACAO_SENHA, parametros, solicitacaoRecuperacaoSenha.getEmail());
        } else if (tipoRecuperacaoSenha.equals(TipoRecuperacaoSenha.NOVO_USUARIO)) {
            emailBO.enviar(TipoAssuntoEmail.NOVO_USUARIO_SISTEMA, parametros, solicitacaoRecuperacaoSenha.getEmail());
        }

    }

    /**
     * Inativa todas as solicitacoes do usuario que estao ATIVAS
     *
     * @param usuario
     */
    public void inativarSolicitacoes(Usuario usuario) {
        //inativar anteriores
        Restrictions restrictions = new Restrictions();
        restrictions.add("usuario", usuario);
        restrictions.add("ativo", true);

        List<SolicitacaoRecuperacaoSenha> solicitacoesRecuperacaoSenhas = solicitacaoRecuperacaoSenhaDAO.list(restrictions);
        if (solicitacoesRecuperacaoSenhas != null) {
            for (SolicitacaoRecuperacaoSenha solicitacaoRecuperacaoSenha : solicitacoesRecuperacaoSenhas) {
                solicitacaoRecuperacaoSenha.setAtivo(false);
                solicitacaoRecuperacaoSenhaDAO.merge(solicitacaoRecuperacaoSenha, false);
            }
        }
    }

    public void save(String email, TipoRecuperacaoSenha tipoRecuperacaoSenha) throws BusinessException {

        if (email == null || email.trim().isEmpty()) {
            throw new BusinessException("required.email");
        }

        Usuario usuario = usuarioDAO.unique("email", email.trim());
        if (usuario == null) {
            throw new BusinessException("business.usuarioNaoEncontradoComEmail");
        }
        if (usuario.getSituacaoUsuario() == null || usuario.getSituacaoUsuario().equals(SituacaoUsuario.INATIVO)) {
            throw new BusinessException("business.usuarioInativo");
        }

        //inativar anteriores
        inativarSolicitacoes(usuario);

        //se o suaurio nao possuir senha cadastrada, deve ser enviado email de novo cadastro
        if (usuario.getSenhaCadastrada() == false && tipoRecuperacaoSenha.equals(TipoRecuperacaoSenha.ESQUECI_SENHA)) {
            tipoRecuperacaoSenha = TipoRecuperacaoSenha.NOVO_USUARIO;
        }

        SolicitacaoRecuperacaoSenha solicitacaoRecuperacaoSenha = new SolicitacaoRecuperacaoSenha();
        solicitacaoRecuperacaoSenha.setEmail(email);
        solicitacaoRecuperacaoSenha.setAtivo(true);
        solicitacaoRecuperacaoSenha.setDataCadastro(new Date());
        if (tipoRecuperacaoSenha.equals(TipoRecuperacaoSenha.ESQUECI_SENHA)) {
            solicitacaoRecuperacaoSenha.setDataValidade(getDataValidade(solicitacaoRecuperacaoSenha.getDataCadastro()));
        }
        solicitacaoRecuperacaoSenha.setTipoRecuperacaoSenha(tipoRecuperacaoSenha);
        solicitacaoRecuperacaoSenha.setUsuario(usuario);
        solicitacaoRecuperacaoSenhaDAO.save(solicitacaoRecuperacaoSenha, false);
        try {
            solicitacaoRecuperacaoSenha.setToken(getToken(solicitacaoRecuperacaoSenha));
            enviarEmail(solicitacaoRecuperacaoSenha, tipoRecuperacaoSenha);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }

    }
}
