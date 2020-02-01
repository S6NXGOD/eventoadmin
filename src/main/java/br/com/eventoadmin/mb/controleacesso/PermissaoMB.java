package br.com.eventoadmin.mb.controleacesso;

import br.com.eventoadmin.bo.controleacesso.PermissaoBO;
import br.com.eventoadmin.modelo.controleacesso.Permissao;
import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import com.xpert.core.crud.AbstractBusinessObject;
import com.xpert.faces.utils.FacesMessageUtils;
import com.xpert.i18n.XpertResourceBundle;
import com.xpert.persistence.exception.DeleteException;
import com.xpert.persistence.query.Restriction;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Ayslan
 */
@ManagedBean
@ViewScoped
public class PermissaoMB extends AbstractBaseBean<Permissao> implements Serializable {

    @EJB
    private PermissaoBO permissaoBO;
    private boolean emCascata;
    private TreeNode permissoes;

    @Override
    public void init() {
        carregarTree();
    }

    public void carregarTree() {
        permissoes = permissaoBO.getTreeNode(null);

    }

    @Override
    public AbstractBusinessObject getBO() {
        return permissaoBO;
    }

    @Override
    public String getDataModelOrder() {
        return "descricao";
    }

    @Override
    public List<Restriction> getDataModelRestrictions() {
        return null;
    }

    public void salvarOrdenacao() {
        permissaoBO.salvarOrdenacao(permissoes);
        carregarTree();
        FacesMessageUtils.sucess();
    }

    public void ativar() {
        permissaoBO.ativar(getEntity(), emCascata);
        carregarTree();
        FacesMessageUtils.sucess();
        emCascata = false;
    }

    public void inativar() {
        permissaoBO.inativar(getEntity(), emCascata);
        carregarTree();
        FacesMessageUtils.sucess();
        emCascata = false;
    }

    public void deleteArvore() {
        try {
            Object id = getId();
            if (getId() != null) {
                getBO().delete(id);
                FacesMessageUtils.sucess();
                id = null;
                //recarregar tree
                carregarTree();
            }
        } catch (DeleteException ex) {
            FacesMessageUtils.error(XpertResourceBundle.get("objectCannotBeDeleted"));
        }
    }

    public boolean isEmCascata() {
        return emCascata;
    }

    public void setEmCascata(boolean emCascata) {
        this.emCascata = emCascata;
    }

    public TreeNode getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(TreeNode permissoes) {
        this.permissoes = permissoes;
    }

}
