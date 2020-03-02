package br.com.eventoadmin.bo;

import com.xpert.core.crud.AbstractBusinessObject;
import br.com.eventoadmin.dao.ClienteDAO;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.eventoadmin.modelo.cliente.Cliente;
import com.xpert.core.validation.UniqueFields;
import com.xpert.utils.Encryption;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Joao Pedro
 */
@Stateless
public class ClienteBO extends AbstractBusinessObject<Cliente> {

    @EJB
    private ClienteDAO clienteDAO;

    @Override
    public ClienteDAO getDAO() {
        return clienteDAO;
    }

    @Override
    public List<UniqueField> getUniqueFields() {
        return new UniqueFields().add(new UniqueField("cpf"), "Já existe um cliente com este CPF");
    }

    @Override
    public void validate(Cliente cliente) throws BusinessException {
    }

    @Override
    public boolean isAudit() {
        return true;
    }

    @Override
    public void save(Cliente cliente) throws BusinessException {
        boolean novo = cliente.getId() == null;

        if (novo) {
            try {
                cliente.setCpf(Encryption.getSHA256(cliente.getCpf()));
            } catch (NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            }
        }
        super.save(cliente);
    }
}
