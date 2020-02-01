package br.com.eventoadmin.servlet;

import br.com.eventoadmin.bo.controleacesso.SolicitacaoRecuperacaoSenhaBO;
import br.com.eventoadmin.mb.controleacesso.SessaoUsuarioMB;
import br.com.eventoadmin.modelo.controleacesso.SolicitacaoRecuperacaoSenha;
import com.xpert.faces.utils.FacesUtils;
import java.io.IOException;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @WebServlet para recuperacao de senha, ao acessar a url mapeada um token valido deve ser enviado para
 * que o usuario informe sua nova senha
 * 
 * @author ayslan
 */
@WebServlet(urlPatterns = "/auth/recuperacao-senha")
public class RecuperacaoSenhaServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(RecuperacaoSenhaServlet.class.getName());
    @EJB
    private SolicitacaoRecuperacaoSenhaBO solicitacaoRecuperacaoSenhaBO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //nao URL deve existir 2 parametros, o email e o token
        String token = request.getParameter("tk");
        String email = request.getParameter("email");

        if (token != null && !token.isEmpty() && email != null && !email.isEmpty()) {
            SolicitacaoRecuperacaoSenha solicitacaoRecuperacaoSenha = solicitacaoRecuperacaoSenhaBO.getSolicitacaoRecuperacaoSenha(token, email);
            if (solicitacaoRecuperacaoSenha != null) {
                //criar faces-context (no servlet o faces context nao existe e esse metodo forca sua criacao)
                FacesContext context = FacesUtils.getFacesContext(request, response);
                Object object = context.getApplication().evaluateExpressionGet(context, "#{sessaoUsuarioMB}", Object.class);
                if (object != null && object instanceof SessaoUsuarioMB) {
                    SessaoUsuarioMB sessaoUsuarioMB = (SessaoUsuarioMB) object;
                    sessaoUsuarioMB.setUser(null);
                    sessaoUsuarioMB.setSolicitacaoRecuperacaoSenha(solicitacaoRecuperacaoSenha);
                    response.sendRedirect(request.getContextPath() + "/cadastroNovaSenha.jsf");
                    return;
                }
            } 
        }

        response.sendRedirect(request.getContextPath());

    }
}
