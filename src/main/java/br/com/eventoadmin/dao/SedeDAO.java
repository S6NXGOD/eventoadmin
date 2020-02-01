package br.com.eventoadmin.dao;

import com.xpert.persistence.dao.BaseDAO;
import br.com.eventoadmin.modelo.sede.Sede;
import javax.ejb.Local;

/**
 *
 * @author Joao Pedro
 */
@Local
public interface SedeDAO extends BaseDAO<Sede> {
    
}
