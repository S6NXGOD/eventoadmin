package br.com.eventoadmin.bo.controleacesso;

import br.com.eventoadmin.dao.controleacesso.PerfilDAO;
import br.com.eventoadmin.dao.controleacesso.PermissaoDAO;
import br.com.eventoadmin.dao.controleacesso.UsuarioDAO;
import br.com.eventoadmin.modelo.controleacesso.Perfil;
import br.com.eventoadmin.modelo.controleacesso.Permissao;
import br.com.eventoadmin.modelo.controleacesso.Usuario;
import br.com.eventoadmin.util.SessaoUtils;
import com.xpert.core.crud.AbstractBusinessObject;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import com.xpert.core.validation.UniqueFields;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;

/**
 *
 * @author Ayslan
 */
@Stateless
public class PerfilBO extends AbstractBusinessObject<Perfil> {

    @EJB
    private PerfilDAO perfilDAO;
    @EJB
    private UsuarioDAO usuarioDAO;
    @EJB
    private PermissaoBO permissaoBO;
    @EJB
    private PermissaoDAO permissaoDAO;

    @Override
    public PerfilDAO getDAO() {
        return perfilDAO;
    }

    @Override
    public List<UniqueField> getUniqueFields() {
        return new UniqueFields().add("descricao");
    }

    /**
     * metodo para garantir que uma permissao nao seja adicionada sem que o
     * usuario logado possua ela
     * 
     * @param perfil
     */
    public void verificarPermissoesPerfil(Perfil perfil) {
        //para o super usuario isso nao e necessario
        if (SessaoUtils.getUser().isSuperUsuario() == false) {
            
            List<Permissao> permissoesUsuarioLogado = permissaoBO.getPermissoes(SessaoUtils.getUser());
            List<Permissao> permissoesBanco = new ArrayList<Permissao>();
            if (perfil.getId() != null) {
                permissoesBanco = permissaoDAO.getPermissoes(perfil);
            }
            //iterar sobre a lista das permissoes selecionadas na tela, caso o usuario nao tenha acesso a ela, ela sera removida
            List<Permissao> permissoesARemover = new ArrayList<Permissao>();
            for (Permissao permissao : perfil.getPermissoes()) {
                //se o usuario nao contem essa permissao e ela nao esta adicionada no banco, remover
                if (!permissoesUsuarioLogado.contains(permissao) && !permissoesBanco.contains(permissao)) {
                    permissoesARemover.add(permissao);
                }
            }
            perfil.getPermissoes().removeAll(permissoesARemover);

        }
    }

    @Override
    public void save(Perfil perfil) throws BusinessException {

        boolean novo = perfil.getId() == null;
        verificarPermissoesPerfil(perfil);
        super.save(perfil);

        if (novo == true) {
            //adicionar o perfil ao usuario logado
            Usuario usuario = SessaoUtils.getUser();
            //recarregar do banco
            if (usuario != null) {
                usuario = usuarioDAO.find(usuario.getId());
                usuario.getPerfis().add(perfil);
                usuarioDAO.merge(usuario);
            }
        }
    }

    @Override
    public void validate(Perfil perfil) throws BusinessException {
    }

    @Override
    public boolean isAudit() {
        return true;
    }
}
