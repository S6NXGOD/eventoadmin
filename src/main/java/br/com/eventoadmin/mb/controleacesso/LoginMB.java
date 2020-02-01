package br.com.eventoadmin.mb.controleacesso;

import br.com.eventoadmin.bo.controleacesso.AcessoSistemaBO;
import br.com.eventoadmin.dao.controleacesso.UsuarioDAO;
import br.com.eventoadmin.modelo.controleacesso.AcessoSistema;
import br.com.eventoadmin.modelo.controleacesso.Usuario;
import com.xpert.i18n.I18N;
import com.xpert.security.bean.SecurityLoginBean;
import com.xpert.security.model.User;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;

/**
 *
 * @author Ayslan
 */
@ManagedBean
public class LoginMB extends SecurityLoginBean {

    @EJB
    private UsuarioDAO usuarioDAO;
    @EJB
    private AcessoSistemaBO acessoSistemaBO;
    @ManagedProperty(value = "#{sessaoUsuarioMB}")
    private SessaoUsuarioMB sessaoUsuarioMB;

    @Override
    public void login() {
        super.login();
    }

    @Override
    public String getRedirectPageWhenSucess() {
        return "/view/home.jsf";
    }

    @Override
    public String getRedirectPageWhenLogout() {
        return "/index.jsf";
    }

    @Override
    public String getUserNotFoundMessage() {
        return I18N.get("business.usuarioOuSenhaInvalidos");
    }

    @Override
    public String getInactiveUserMessage() {
        return I18N.get("business.usuarioInativo");
    }

    @Override
    public EntityManager getEntityManager() {
        return usuarioDAO.getEntityManager();

    }

    @Override
    public boolean isLoginUpperCase() {
        return true;
    }

    @Override
    public boolean isValidateWhenNoRolesFound() {
        return false;
    }

    /**
     * Registrar o acesso do usuario atraves da classe AcessoSistema
     * 
     * @see AcessoSistema
     * @param user 
     */
    @Override
    public void onSucess(User user) {
        Usuario usuario = (Usuario) user;
        acessoSistemaBO.save(usuario);
    }

    @Override
    public Class getUserClass() {
        return Usuario.class;
    }

    @Override
    public SessaoUsuarioMB getUserSession() {
        return getSessaoUsuarioMB();
    }

    public SessaoUsuarioMB getSessaoUsuarioMB() {
        return sessaoUsuarioMB;
    }

    public void setSessaoUsuarioMB(SessaoUsuarioMB sessaoUsuarioMB) {
        this.sessaoUsuarioMB = sessaoUsuarioMB;
    }
}
