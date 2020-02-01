package br.com.eventoadmin.dao.controleacesso;

import com.xpert.persistence.dao.BaseDAO;
import br.com.eventoadmin.modelo.controleacesso.AcessoSistema;
import javax.ejb.Local;

/**
 *
 * @author ayslan
 */
@Local
public interface AcessoSistemaDAO extends BaseDAO<AcessoSistema> {
    
}
