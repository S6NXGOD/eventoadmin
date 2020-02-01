package br.com.eventoadmin.mb;


import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.eventoadmin.bo.PacoteBO;
import br.com.eventoadmin.modelo.pacote.Pacote;

/**
 *
 * @author Joao Pedro
 */
@ManagedBean
@ViewScoped
public class PacoteMB extends AbstractBaseBean<Pacote> implements Serializable {

    @EJB
    private PacoteBO pacoteBO;

    @Override
    public PacoteBO getBO() {
        return pacoteBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }
}
