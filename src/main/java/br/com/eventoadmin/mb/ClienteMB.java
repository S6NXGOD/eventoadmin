package br.com.eventoadmin.mb;

import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.eventoadmin.bo.ClienteBO;
import br.com.eventoadmin.modelo.cliente.Cliente;
import br.com.eventoadmin.util.Utils;
import com.xpert.faces.utils.FacesMessageUtils;
import java.io.IOException;
import javax.faces.context.FacesContext;

/**
 *
 * @author Joao Pedro
 */
@ManagedBean
@ViewScoped
public class ClienteMB extends AbstractBaseBean<Cliente> implements Serializable {

    @EJB
    private ClienteBO clienteBO;

    @Override
    public ClienteBO getBO() {
        return clienteBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id";
    }

    public void enviarMensagemZap(Cliente cliente) throws IOException {
        if (cliente != null) {
            if (Utils.isNullOrEmpty(cliente.getWhatsapp())) {
                FacesMessageUtils.error("Não existe Whatsapp cadastrado para este cliente!");
            } else {
                String fone = cliente.getWhatsapp().replace(" ", "").replace("(", "").replace(")", "")
                        .replace("-", "");
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("https://api.whatsapp.com/send?phone=55PHONE&text=Oi"
                                .replace("PHONE", fone));
            }
        } else {
            FacesMessageUtils.error("Whatsapp do cliente não encontrado.");
        }
    }

}
