package br.com.eventoadmin.bo;

import com.xpert.core.crud.AbstractBusinessObject;
import br.com.eventoadmin.dao.PacoteDAO;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.eventoadmin.modelo.pacote.Pacote;
import com.xpert.core.validation.UniqueFields;

/**
 *
 * @author Joao Pedro
 */
@Stateless
public class PacoteBO extends AbstractBusinessObject<Pacote> {

    @EJB
    private PacoteDAO pacoteDAO;
    
    @Override
    public PacoteDAO getDAO() {
        return pacoteDAO;
    }

    @Override
    public List<UniqueField> getUniqueFields() {
        return new UniqueFields().add("nome");
    }

    @Override
    public void validate(Pacote pacote) throws BusinessException {
    }

    @Override
    public boolean isAudit() {
        return true;
    }

}
