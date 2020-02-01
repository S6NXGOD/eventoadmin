package br.com.eventoadmin.bo;

import com.xpert.core.crud.AbstractBusinessObject;
import br.com.eventoadmin.dao.TipoReservaDAO;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.eventoadmin.modelo.reserva.TipoReserva;

/**
 *
 * @author Joao Pedro
 */
@Stateless
public class TipoReservaBO extends AbstractBusinessObject<TipoReserva> {

    @EJB
    private TipoReservaDAO tipoReservaDAO;
    
    @Override
    public TipoReservaDAO getDAO() {
        return tipoReservaDAO;
    }

    @Override
    public List<UniqueField> getUniqueFields() {
        return null;
    }

    @Override
    public void validate(TipoReserva tipoReserva) throws BusinessException {
    }

    @Override
    public boolean isAudit() {
        return true;
    }

}
