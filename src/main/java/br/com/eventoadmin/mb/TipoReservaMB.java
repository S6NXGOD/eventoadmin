package br.com.eventoadmin.mb;


import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.eventoadmin.bo.TipoReservaBO;
import br.com.eventoadmin.modelo.reserva.TipoReserva;

/**
 *
 * @author Joao Pedro
 */
@ManagedBean
@ViewScoped
public class TipoReservaMB extends AbstractBaseBean<TipoReserva> implements Serializable {

    @EJB
    private TipoReservaBO tipoReservaBO;

    @Override
    public TipoReservaBO getBO() {
        return tipoReservaBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }
}
