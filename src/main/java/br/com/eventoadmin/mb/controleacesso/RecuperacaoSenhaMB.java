package br.com.eventoadmin.mb.controleacesso;

import br.com.eventoadmin.bo.controleacesso.SolicitacaoRecuperacaoSenhaBO;
import br.com.eventoadmin.modelo.controleacesso.TipoRecuperacaoSenha;
import com.xpert.core.exception.BusinessException;
import com.xpert.faces.utils.FacesMessageUtils;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ayslan
 */
@ManagedBean
public class RecuperacaoSenhaMB {
    
    @Email(message = "business.emailInvalido")
    @NotBlank(message = "required.email")
    private String email;
    @EJB
    private SolicitacaoRecuperacaoSenhaBO solicitacaoRecuperacaoSenhaBO;
    
    public void save(){
        try {
            solicitacaoRecuperacaoSenhaBO.save(email, TipoRecuperacaoSenha.ESQUECI_SENHA);
            FacesMessageUtils.info("solicitacaoRecuperacaoSenha.instrucoesEnviadas");
            email = "";
        } catch (BusinessException ex) {
            FacesMessageUtils.error(ex);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
