package br.com.eventoadmin.dao.impl;

import br.com.eventoadmin.application.BaseDAOImpl;
import br.com.eventoadmin.dao.PaisDAO;
import br.com.eventoadmin.modelo.cadastro.Pais;
import javax.ejb.Stateless;

/**
 *
 * @author Joao Pedro
 */
@Stateless
public class PaisDAOImpl extends BaseDAOImpl<Pais> implements PaisDAO {

    @Override
    public Class getEntityClass() {
        return Pais.class;
    }

}
