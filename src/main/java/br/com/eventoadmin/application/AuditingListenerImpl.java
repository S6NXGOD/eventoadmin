package br.com.eventoadmin.application;

import br.com.eventoadmin.mb.controleacesso.SessaoUsuarioMB;
import br.com.eventoadmin.modelo.audit.Auditing;
import com.xpert.audit.AbstractAuditingListener;
import com.xpert.audit.model.AbstractAuditing;
import com.xpert.faces.utils.FacesUtils;
import javax.faces.context.FacesContext;

/**
 * Implmentacao do AbstractAuditingListener do xpert-framework, aqui serah definido o usuario
 * que esta realizando a acao, ele deve ser pego do bean de sessao #{sessaoUsuarioMB}
 * 
 * @author
 */
public class AuditingListenerImpl implements AbstractAuditingListener {

    @Override
    public void onSave(AbstractAuditing abstractAuditing) {
        Auditing auditing = (Auditing) abstractAuditing;
        if (FacesContext.getCurrentInstance() != null) {
            SessaoUsuarioMB sessaoUsuarioMB = FacesUtils.getBeanByEl("#{sessaoUsuarioMB}");
            if (sessaoUsuarioMB != null) {
                auditing.setUsuario(sessaoUsuarioMB.getUser());
            }
        }
    }
}
