package br.com.eventoadmin.dao.controleacesso.impl;

import br.com.eventoadmin.application.BaseDAOImpl;
import br.com.eventoadmin.dao.controleacesso.HistoricoSituacaoUsuarioDAO;
import br.com.eventoadmin.modelo.controleacesso.HistoricoSituacaoUsuario;
import javax.ejb.Stateless;

/**
 *
 * @author ayslan
 */
@Stateless
public class HistoricoSituacaoUsuarioDAOImpl extends BaseDAOImpl<HistoricoSituacaoUsuario> implements HistoricoSituacaoUsuarioDAO {

    @Override
    public Class getEntityClass() {
        return HistoricoSituacaoUsuario.class;
    }
}
