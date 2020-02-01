package br.com.eventoadmin.dao.controleacesso.impl;

import br.com.eventoadmin.application.BaseDAOImpl;
import br.com.eventoadmin.dao.controleacesso.AcessoSistemaDAO;
import br.com.eventoadmin.modelo.controleacesso.AcessoSistema;
import javax.ejb.Stateless;

/**
 *
 * @author ayslan
 */
@Stateless
public class AcessoSistemaDAOImpl extends BaseDAOImpl<AcessoSistema> implements AcessoSistemaDAO {

    @Override
    public Class getEntityClass() {
        return AcessoSistema.class;
    }
    
    
}
