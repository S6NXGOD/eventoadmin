package br.com.eventoadmin.bo;

import com.xpert.core.crud.AbstractBusinessObject;
import br.com.eventoadmin.dao.EspacoDAO;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.eventoadmin.modelo.sede.Espaco;

/**
 *
 * @author Joao Pedro
 */
@Stateless
public class EspacoBO extends AbstractBusinessObject<Espaco> {

    @EJB
    private EspacoDAO espacoDAO;
    
    @Override
    public EspacoDAO getDAO() {
        return espacoDAO;
    }

    @Override
    public List<UniqueField> getUniqueFields() {
        return null;
    }

    @Override
    public void validate(Espaco espaco) throws BusinessException {
    }

    @Override
    public boolean isAudit() {
        return true;
    }

}
