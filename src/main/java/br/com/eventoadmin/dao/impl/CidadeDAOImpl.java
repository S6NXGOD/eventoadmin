package br.com.eventoadmin.dao.impl;

import br.com.eventoadmin.application.BaseDAOImpl;
import br.com.eventoadmin.dao.CidadeDAO;
import br.com.eventoadmin.modelo.cadastro.Cidade;
import javax.ejb.Stateless;

/**
 *
 * @author Joao Pedro
 */
@Stateless
public class CidadeDAOImpl extends BaseDAOImpl<Cidade> implements CidadeDAO {

    @Override
    public Class getEntityClass() {
        return Cidade.class;
    }

}
