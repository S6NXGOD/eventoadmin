package br.com.eventoadmin.dao.impl;

import br.com.eventoadmin.application.BaseDAOImpl;
import br.com.eventoadmin.dao.EstadoDAO;
import br.com.eventoadmin.modelo.cadastro.Estado;
import javax.ejb.Stateless;

/**
 *
 * @author Joao Pedro
 */
@Stateless
public class EstadoDAOImpl extends BaseDAOImpl<Estado> implements EstadoDAO {

    @Override
    public Class getEntityClass() {
        return Estado.class;
    }

}
