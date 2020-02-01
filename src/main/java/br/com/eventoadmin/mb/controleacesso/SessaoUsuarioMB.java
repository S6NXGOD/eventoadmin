package br.com.eventoadmin.mb.controleacesso;

import br.com.eventoadmin.bo.controleacesso.UsuarioMenuBO;
import br.com.eventoadmin.bo.controleacesso.PermissaoBO;
import br.com.eventoadmin.dao.controleacesso.PermissaoDAO;
import br.com.eventoadmin.modelo.controleacesso.Permissao;
import br.com.eventoadmin.modelo.controleacesso.SolicitacaoRecuperacaoSenha;
import br.com.eventoadmin.modelo.controleacesso.Usuario;
import com.xpert.security.model.User;
import com.xpert.security.session.AbstractUserSession;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.menu.MenuModel;

/**
 * @ManagedBean que guarda o usuario logado e suas permissoess
 *
 * @author ayslan
 */
@ManagedBean
@SessionScoped
public class SessaoUsuarioMB extends AbstractUserSession implements Serializable {

    @EJB
    private PermissaoDAO permissaoDAO;
    @EJB
    private UsuarioMenuBO usuarioMenuBO;
    @EJB
    private PermissaoBO permissaoBO;
    private Usuario user;
    private List<Permissao> roles;
    private List<Permissao> atalhos;
    private MenuModel menuModel;
    private SolicitacaoRecuperacaoSenha solicitacaoRecuperacaoSenha;

    @Override
    public void createSession() {
        //pegar permissoes do usuario
        roles = permissaoBO.getPermissoes(user, true);
        //pegar atalhos do usuario
        atalhos = permissaoDAO.getPermissoesAtalhos(user);
        //criar o menu
        criarMenu();
        criarCaminhoPermissao();
        super.createSession();
        //iniciar lazy
        user.setPerfis(permissaoDAO.getInitialized(user.getPerfis()));
    }

    public void criarMenu() {
        menuModel = usuarioMenuBO.criarMenu(roles);
    }

    public void criarCaminhoPermissao() {
        permissaoBO.criarCaminhoPermissao(atalhos, false);
        permissaoBO.criarCaminhoPermissao(roles, true);
    }

    public List<Permissao> pesquisarPermissao(String query) {
        return permissaoBO.pesquisarPermissao(query, roles);
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    @Override
    public void setUser(User user) {
        this.user = (Usuario) user;
    }

    @Override
    public Usuario getUser() {
        return user;
    }

    @Override
    public List getRoles() {
        return roles;
    }

    public List<Permissao> getAtalhos() {
        return atalhos;
    }

    public void setAtalhos(List<Permissao> atalhos) {
        this.atalhos = atalhos;
    }

    public SolicitacaoRecuperacaoSenha getSolicitacaoRecuperacaoSenha() {
        return solicitacaoRecuperacaoSenha;
    }

    public void setSolicitacaoRecuperacaoSenha(SolicitacaoRecuperacaoSenha solicitacaoRecuperacaoSenha) {
        this.solicitacaoRecuperacaoSenha = solicitacaoRecuperacaoSenha;
    }
}
