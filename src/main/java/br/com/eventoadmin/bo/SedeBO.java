package br.com.eventoadmin.bo;

import com.xpert.core.crud.AbstractBusinessObject;
import br.com.eventoadmin.dao.SedeDAO;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.eventoadmin.modelo.sede.Sede;

/**
 *
 * @author Joao Pedro
 */
@Stateless
public class SedeBO extends AbstractBusinessObject<Sede> {

    @EJB
    private SedeDAO sedeDAO;
    
    @Override
    public SedeDAO getDAO() {
        return sedeDAO;
    }

    @Override
    public List<UniqueField> getUniqueFields() {
        return null;
    }

    @Override
    public void validate(Sede sede) throws BusinessException {
    }

    @Override
    public boolean isAudit() {
        return true;
    }

}
