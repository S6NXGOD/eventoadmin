package br.com.eventoadmin.dao.impl;

import br.com.eventoadmin.application.BaseDAOImpl;
import br.com.eventoadmin.dao.TipoReservaDAO;
import br.com.eventoadmin.modelo.reserva.TipoReserva;
import javax.ejb.Stateless;

/**
 *
 * @author Joao Pedro
 */
@Stateless
public class TipoReservaDAOImpl extends BaseDAOImpl<TipoReserva> implements TipoReservaDAO {

    @Override
    public Class getEntityClass() {
        return TipoReserva.class;
    }

}
