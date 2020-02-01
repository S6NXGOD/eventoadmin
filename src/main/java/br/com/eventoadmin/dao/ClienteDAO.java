package br.com.eventoadmin.dao;

import com.xpert.persistence.dao.BaseDAO;
import br.com.eventoadmin.modelo.cliente.Cliente;
import javax.ejb.Local;

/**
 *
 * @author Joao Pedro
 */
@Local
public interface ClienteDAO extends BaseDAO<Cliente> {
    
}
