package br.com.eventoadmin.bo.controleacesso;

import br.com.eventoadmin.util.SessaoUtils;
import br.com.eventoadmin.dao.controleacesso.HistoricoSituacaoUsuarioDAO;
import br.com.eventoadmin.dao.controleacesso.PerfilDAO;
import br.com.eventoadmin.dao.controleacesso.UsuarioDAO;
import br.com.eventoadmin.modelo.controleacesso.HistoricoSituacaoUsuario;
import br.com.eventoadmin.modelo.controleacesso.Perfil;
import br.com.eventoadmin.modelo.controleacesso.SituacaoUsuario;
import br.com.eventoadmin.modelo.controleacesso.TipoRecuperacaoSenha;
import br.com.eventoadmin.modelo.controleacesso.Usuario;
import com.xpert.core.crud.AbstractBusinessObject;
import com.xpert.persistence.dao.BaseDAO;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import com.xpert.core.validation.UniqueFields;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.xpert.utils.Encryption;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author Ayslan
 */
@Stateless
public class UsuarioBO extends AbstractBusinessObject<Usuario> {

    private static final int TAMANHO_SENHA_ALEATORIA = 8;
    @EJB
    private UsuarioDAO usuarioDAO;
    @EJB
    private PerfilDAO perfilDAO;
    @EJB
    private HistoricoSituacaoUsuarioDAO historicoSituacaoUsuarioDAO;
    @EJB
    private SolicitacaoRecuperacaoSenhaBO solicitacaoRecuperacaoSenhaBO;

    @Override
    public BaseDAO getDAO() {
        return usuarioDAO;
    }

    /**
     * nao pode-se repetir o email , cpf e login
     *
     * @return
     */
    @Override
    public List<UniqueField> getUniqueFields() {
        return new UniqueFields()
                .add("cpf")
                .add("userLogin")
                .add("email");
    }

    public void enviarSenhaCadastro(Usuario usuario) throws BusinessException {
        solicitacaoRecuperacaoSenhaBO.save(usuario.getEmail(), TipoRecuperacaoSenha.NOVO_USUARIO);
        usuario.setEmailCadastroEnviado(true);
        usuarioDAO.merge(usuario);
    }

    @Override
    public void save(Usuario usuario) throws BusinessException {
        boolean novo = usuario.getId() == null;

        SituacaoUsuario situacaoUsuarioAnterior = null;
        if (!novo) {
            //pegar situacao do banco
            situacaoUsuarioAnterior = (SituacaoUsuario) usuarioDAO.findAttribute("situacaoUsuario", usuario.getId());
        }

        if (novo) {
            try {
                usuario.setDataCadastro(new Date());
                //setar senha aleatoria para nao deixar campo em branco
                usuario.setUserPassword(Encryption.getSHA256(RandomStringUtils.random(10)));
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            }
        }
        //super usuario pode remover o perfil mesmo sem te-lo
        if (SessaoUtils.getUser().isSuperUsuario() == false) {
            /*
             caso nao venha o perfil marcado e esse o usuario que estiver cadastrando nao possuir esse perfil, ele deve ser adicionado, 
             pois nesse caso o usuario logado nao tem acesso a remover o perfil q ele nao tem acesso
             */
            List<Perfil> perfisUsuarioLogado = SessaoUtils.getUser().getPerfis();
            List<Perfil> perfisNovosCadastro = usuario.getPerfis();
            if (usuario.getId() != null) {
                List<Perfil> perfisAtuaisUsuario = perfilDAO.getPerfis(usuario);
                for (Perfil perfil : perfisAtuaisUsuario) {
                    //se nao conter, mas estiver removendo
                    if (!perfisNovosCadastro.contains(perfil) && !perfisUsuarioLogado.contains(perfil) && perfisAtuaisUsuario.contains(perfil)) {
                        perfisNovosCadastro.add(perfil);
                    }
                }
            }
        }
        //salvar usuario
        super.save(usuario);

        //caso nao exista uma situacao anterior, ou ele for diferente da nova, salvar um historico
        if (situacaoUsuarioAnterior == null || !situacaoUsuarioAnterior.equals(usuario.getSituacaoUsuario())) {
            HistoricoSituacaoUsuario historicoSituacaoUsuario = new HistoricoSituacaoUsuario();
            historicoSituacaoUsuario.setDataSituacao(new Date());
            historicoSituacaoUsuario.setSituacaoUsuario(usuario.getSituacaoUsuario());
            historicoSituacaoUsuario.setUsuario(usuario);
            historicoSituacaoUsuario.setUsuarioAlteracao(SessaoUtils.getUser());
            historicoSituacaoUsuarioDAO.merge(historicoSituacaoUsuario);
            //atualizar lista do objeto usuario
            usuario.setHistoricosSituacao(usuarioDAO.getInitialized(usuario.getHistoricosSituacao()));
            if (usuario.getHistoricosSituacao() == null) {
                usuario.setHistoricosSituacao(new ArrayList<HistoricoSituacaoUsuario>());
            }
            usuario.getHistoricosSituacao().add(historicoSituacaoUsuario);
        }
    }

    public static String getSenhaAleatoria() {
        return RandomStringUtils.randomAlphanumeric(TAMANHO_SENHA_ALEATORIA);
    }

    @Override
    public void validate(Usuario usuario) throws BusinessException {
    }

    @Override
    public boolean isAudit() {
        return true;
    }

    /**
     * retorna o usuario a partir do cpf
     *
     * @param cpf
     * @return
     */
    public Usuario getUsuario(String cpf) {
        return usuarioDAO.unique("cpf", cpf);
    }
}
