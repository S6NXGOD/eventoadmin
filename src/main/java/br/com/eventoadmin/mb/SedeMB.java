package br.com.eventoadmin.mb;


import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.eventoadmin.bo.SedeBO;
import br.com.eventoadmin.modelo.sede.Sede;

/**
 *
 * @author Joao Pedro
 */
@ManagedBean
@ViewScoped
public class SedeMB extends AbstractBaseBean<Sede> implements Serializable {

    @EJB
    private SedeBO sedeBO;

    @Override
    public SedeBO getBO() {
        return sedeBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }
}
