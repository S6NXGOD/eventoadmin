package br.com.eventoadmin.dao;

import com.xpert.persistence.dao.BaseDAO;
import br.com.eventoadmin.modelo.pacote.Pacote;
import javax.ejb.Local;

/**
 *
 * @author Joao Pedro
 */
@Local
public interface PacoteDAO extends BaseDAO<Pacote> {
    
}
