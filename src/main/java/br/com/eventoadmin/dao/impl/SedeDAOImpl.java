package br.com.eventoadmin.dao.impl;

import br.com.eventoadmin.application.BaseDAOImpl;
import br.com.eventoadmin.dao.SedeDAO;
import br.com.eventoadmin.modelo.sede.Sede;
import javax.ejb.Stateless;

/**
 *
 * @author Joao Pedro
 */
@Stateless
public class SedeDAOImpl extends BaseDAOImpl<Sede> implements SedeDAO {

    @Override
    public Class getEntityClass() {
        return Sede.class;
    }

}
