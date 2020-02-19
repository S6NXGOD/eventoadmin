package br.com.eventoadmin.bo;

import com.xpert.core.crud.AbstractBusinessObject;
import br.com.eventoadmin.dao.SedeDAO;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.com.eventoadmin.modelo.sede.Sede;
import com.xpert.core.validation.UniqueFields;

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
        return new UniqueFields().add(new UniqueField("cnpj"), "Já existe uma sede com este CNPJ")
                .add(new UniqueField("razaoSocial"),"Já existe uma sede com esta Razão Social");
    }

    @Override
    public void validate(Sede sede) throws BusinessException {
    }

    @Override
    public boolean isAudit() {
        return true;
    }

}
