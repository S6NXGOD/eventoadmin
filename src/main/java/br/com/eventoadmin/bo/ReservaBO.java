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

    //Esta regra faz com que o sistema nao permita a criaçao de uma reserva com o mesmo checkin e checkout
    @Override
    public List<UniqueField> getUniqueFields() {
        return new UniqueFields().add(new UniqueField("checkin", "checkout"), "Já existe uma reserva nas datas selecionadas").add("checkin").add("checkout");
    }

    @Override
    public void validate(Reserva reserva) throws BusinessException {
        BusinessException exception = new BusinessException();
        
        //Se o checkin for antes da data atual o sistema nao ira permitir o cadastro da reserva
        if (reserva.getCheckin().before(new Date())) {
            exception.add("business.checkinNewDate");
        }
        //Se o checkin for depois do checkout o sistema nao ira permitir o cadastro da reserva
        if (reserva.getCheckin().after(reserva.getCheckout())) {
            exception.add("business.checkinDepois");
        }
        //Toda vez que o metodo for chamado as regras serao auditadas
        exception.check();
    }

    @Override
    public boolean isAudit() {
        return true;
    }

}
