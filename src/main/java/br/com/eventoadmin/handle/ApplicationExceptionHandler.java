package br.com.eventoadmin.handle;

import br.com.eventoadmin.util.SessaoUtils;
import br.com.eventoadmin.bo.configuracao.ErroSistemaBO;
import br.com.eventoadmin.modelo.configuracao.ErroSistema;
import com.xpert.faces.utils.FacesMessageUtils;
import com.xpert.faces.utils.FacesUtils;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

/**
 * ExceptionHandler que trata os erros da aplicacao, caso seja um ViewExpiredException o usuario deve ser
 * redirecionado para a pagina inicial caso seja outra excecao essa deve ser registrada usando a classe ErroSistema
 * 
 * @see ErroSistema
 * @author ayslan
 */
public class ApplicationExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;
    private static final String INDEX = "/index.jsf";
    private static final String ERRO = "/erro.jsf";
    private static final Logger logger = Logger.getLogger(ApplicationExceptionHandler.class.getName());
    private String pilhaErro;
    private ErroSistemaBO erroSistemaBO;

    public ApplicationExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() throws FacesException {

        //Iterate over all unhandeled exceptions
        Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();

        while (i.hasNext()) {

            Throwable throwable = i.next().getContext().getException();
            logger.log(Level.SEVERE, "", throwable);
            pilhaErro = ErroSistemaBO.getStackTrace(throwable);

            //here you do what ever you want with exception
            try {
                if (throwable instanceof ViewExpiredException) {
                    FacesUtils.redirect(INDEX);
                } else {
                    salvarErro(throwable);
                }
            } catch (Throwable ex) {
                //caso aconteça alguma exceção ao salva o erro, exibir o erro no log e mostrar na tela
                logger.log(Level.SEVERE, "Erro ao registrar exceção lançada", ex);
                FacesUtils.addToSession("erroSistema", pilhaErro);
                FacesUtils.redirect(ERRO);
            } finally {
                //sempre remover a execeção
                i.remove();
            }
        }
        //let the parent handle the rest
        getWrapped().handle();

    }

    public void salvarErro(Throwable throwable) throws NamingException {
        //lookup do EJB
        InitialContext ctx = new InitialContext();
        erroSistemaBO = (ErroSistemaBO) ctx.lookup("java:comp/env/ejb/ErroSistemaBO");
        HttpServletRequest request = FacesUtils.getRequest();
        ErroSistema erroSistema = erroSistemaBO.save(SessaoUtils.getUser(), pilhaErro, request.getServletPath());
        FacesMessageUtils.fatal("erroInesperadoComProtocolo", erroSistema.getId().toString());
        logger.info("Erro salvo no ApplicationExceptionHandler");
    }
}
