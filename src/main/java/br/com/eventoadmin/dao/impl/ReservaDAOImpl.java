package br.com.eventoadmin.dao.impl;

import br.com.eventoadmin.application.BaseDAOImpl;
import br.com.eventoadmin.dao.ReservaDAO;
import br.com.eventoadmin.modelo.reserva.Reserva;
import javax.ejb.Stateless;

/**
 *
 * @author Joao Pedro
 */
@Stateless
public class ReservaDAOImpl extends BaseDAOImpl<Reserva> implements ReservaDAO {

    @Override
    public Class getEntityClass() {
        return Reserva.class;
    }

}
