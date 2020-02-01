package br.com.eventoadmin.bo;

import com.xpert.core.crud.AbstractBusinessObject;
import br.com.eventoadmin.dao.ReservaDAO;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.eventoadmin.modelo.reserva.Reserva;
import com.xpert.core.validation.UniqueFields;
import java.util.Date;

/**
 *
 * @author Joao Pedro
 */
@Stateless
public class ReservaBO extends AbstractBusinessObject<Reserva> {

    @EJB
    private ReservaDAO reservaDAO;

    @Override
    public ReservaDAO getDAO() {
        return reservaDAO;
    }

    @Override
    public List<UniqueField> getUniqueFields() {
        return new UniqueFields().add(new UniqueField("checkin", "checkout"), "Já existe uma reserva nas datas selecionadas");
    }

    @Override
    public void validate(Reserva reserva) throws BusinessException {
        BusinessException exception = new BusinessException();
        if (reserva.getCheckin().before(new Date())) {
            exception.add("business.checkinNewDate");
        }
        if (reserva.getCheckin().after(reserva.getCheckout())) {
            exception.add("business.checkinDepois");
        }
        exception.check();
    }

    @Override
    public boolean isAudit() {
        return true;
    }

}
