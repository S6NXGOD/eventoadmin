package br.com.eventoadmin.dao;

import com.xpert.persistence.dao.BaseDAO;
import br.com.eventoadmin.modelo.reserva.Reserva;
import javax.ejb.Local;

/**
 *
 * @author Joao Pedro
 */
@Local
public interface ReservaDAO extends BaseDAO<Reserva> {
    
}
