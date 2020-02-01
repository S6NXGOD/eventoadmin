package br.com.eventoadmin.dao.impl;

import br.com.eventoadmin.application.BaseDAOImpl;
import br.com.eventoadmin.dao.EspacoDAO;
import br.com.eventoadmin.modelo.sede.Espaco;
import javax.ejb.Stateless;

/**
 *
 * @author Joao Pedro
 */
@Stateless
public class EspacoDAOImpl extends BaseDAOImpl<Espaco> implements EspacoDAO {

    @Override
    public Class getEntityClass() {
        return Espaco.class;
    }

}
