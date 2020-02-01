package br.com.eventoadmin.mb;


import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.eventoadmin.bo.EspacoBO;
import br.com.eventoadmin.modelo.sede.Espaco;

/**
 *
 * @author Joao Pedro
 */
@ManagedBean
@ViewScoped
public class EspacoMB extends AbstractBaseBean<Espaco> implements Serializable {

    @EJB
    private EspacoBO espacoBO;

    @Override
    public EspacoBO getBO() {
        return espacoBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }
}
