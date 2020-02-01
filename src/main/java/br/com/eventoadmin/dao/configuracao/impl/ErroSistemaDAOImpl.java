package br.com.eventoadmin.dao.configuracao.impl;

import br.com.eventoadmin.application.BaseDAOImpl;
import br.com.eventoadmin.dao.configuracao.ErroSistemaDAO;
import br.com.eventoadmin.modelo.configuracao.ErroSistema;
import javax.ejb.Stateless;

/**
 *
 * @author Ayslan
 */
@Stateless
public class ErroSistemaDAOImpl extends BaseDAOImpl<ErroSistema> implements ErroSistemaDAO {

    @Override
    public Class getEntityClass() {
        return ErroSistema.class;
    }
    
    
    
}
