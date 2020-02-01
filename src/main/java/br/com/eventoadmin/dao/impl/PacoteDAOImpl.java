package br.com.eventoadmin.dao.impl;

import br.com.eventoadmin.application.BaseDAOImpl;
import br.com.eventoadmin.dao.PacoteDAO;
import br.com.eventoadmin.modelo.pacote.Pacote;
import javax.ejb.Stateless;

/**
 *
 * @author Joao Pedro
 */
@Stateless
public class PacoteDAOImpl extends BaseDAOImpl<Pacote> implements PacoteDAO {

    @Override
    public Class getEntityClass() {
        return Pacote.class;
    }

}
