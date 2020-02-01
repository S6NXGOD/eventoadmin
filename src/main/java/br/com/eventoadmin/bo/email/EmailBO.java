package br.com.eventoadmin.bo.email;

import br.com.eventoadmin.bo.configuracao.ErroSistemaBO;
import br.com.eventoadmin.modelo.email.Attachment;
import br.com.eventoadmin.modelo.email.ConfiguracaoEmail;
import br.com.eventoadmin.modelo.email.ModeloEmail;
import br.com.eventoadmin.modelo.email.TipoAssuntoEmail;
import com.xpert.core.exception.BusinessException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author ayslan
 */
@Stateless
public class EmailBO {

    private static final Configuration cfg = new Configuration();
    private static final Logger logger = Logger.getLogger(EmailBO.class.getName());
    @EJB
    private ModeloEmailBO modeloEmailBO;
    @EJB
    private ErroSistemaBO erroSistemaBO;

    /**
     * Enviar email assincrono a partir dos parametros passados
     *
     * @param tipoAssuntoEmail Tipo de Assunto do email. Usado para recuperar o
     * modelo do email
     * @param parametros Parametros para serem passados para o template
     * @param destinatario Email de destino da mensagem
     */
    @Asynchronous
    public void enviarAssincrono(TipoAssuntoEmail tipoAssuntoEmail, Map<String, Object> parametros, String destinatario) {
        try {
            enviar(tipoAssuntoEmail, parametros, destinatario);
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
    }

    /**
     * Enviar email a partir dos parametros passados
     *
     * @param tipoAssuntoEmail Tipo de Assunto do email. Usado para recuperar o
     * modelo do email
     * @param parametros Parametros para serem passados para o template
     * @param destinatario Email de destino da mensagem
     * @throws BusinessException
     */
    public void enviar(TipoAssuntoEmail tipoAssuntoEmail, Map<String, Object> parametros, String destinatario) throws BusinessException {
        enviar(tipoAssuntoEmail, parametros, destinatario, null);
    }

    /**
     * Enviar email a partir dos parametros passados
     *
     * @param tipoAssuntoEmail Tipo de Assunto do email. Usado para recuperar o
     * modelo do email
     * @param parametros Parametros para serem passados para o template
     * @param destinatario Email de destino da mensagem
     * @param anexos
     * @throws BusinessException
     */
    public void enviar(TipoAssuntoEmail tipoAssuntoEmail, Map<String, Object> parametros, String destinatario, List<Attachment> anexos) throws BusinessException {
        ModeloEmail modeloEmail = modeloEmailBO.getModeloEmail(tipoAssuntoEmail);
        enviar(modeloEmail, parametros, destinatario, anexos);
    }

    /**
     * Enviar email assincrono a partir dos parametros passados
     *
     * @param modeloEmail Modelo a ser usado de email
     * @param parametros Parametros para serem passados para o template
     * @param destinatario Email de destino da mensagem
     */
    @Asynchronous
    public void enviarAssincrono(ModeloEmail modeloEmail, Map<String, Object> parametros, String destinatario) {
        try {
            enviar(modeloEmail, parametros, destinatario);
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
    }

    /**
     * Enviar email assincrono a partir dos parametros passados
     *
     * @param modeloEmail Modelo a ser usado de email
     * @param parametros Parametros para serem passados para o template
     * @param destinatario Email de destino da mensagem
     * @param anexos Anexos do email
     */
    @Asynchronous
    public void enviarAssincrono(ModeloEmail modeloEmail, Map<String, Object> parametros, String destinatario, List<Attachment> anexos) {
        try {
            enviar(modeloEmail, parametros, destinatario, anexos);
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
    }

    /**
     * Enviar email a partir dos parametros passados
     *
     * @param modeloEmail Modelo a ser usado de email
     * @param parametros Parametros para serem passados para o template
     * @param destinatario Email de destino da mensagem
     * @throws BusinessException
     */
    public void enviar(ModeloEmail modeloEmail, Map<String, Object> parametros, String destinatario) throws BusinessException {
        enviar(modeloEmail, parametros, destinatario, null);
    }

    /**
     * Enviar email a partir dos parametros passados
     *
     * @param modeloEmail Modelo a ser usado de email
     * @param parametros Parametros para serem passados para o template
     * @param destinatario Email de destino da mensagem
     * @param anexos Anexos do email
     * @throws BusinessException
     */
    public void enviar(ModeloEmail modeloEmail, Map<String, Object> parametros, String destinatario, List<Attachment> anexos) throws BusinessException {
        String mensagem = EmailBO.getMensagem(modeloEmail.getLayout(), parametros);
        String assunto = EmailBO.getMensagem(modeloEmail.getAssunto(), parametros);
        enviar(assunto, mensagem, modeloEmail.getConfiguracaoEmail(), destinatario, anexos);
    }

    /**
     * Enviar email assincrono a partir dos parametros passados
     *
     * @param assunto Assunto do Email
     * @param mensagem Mensagem do Email
     * @param configuracaoEmail
     * @param destinatario Email de destino
     */
    @Asynchronous
    public void enviarAssincrono(String assunto, String mensagem, ConfiguracaoEmail configuracaoEmail, String destinatario) {
        try {
            enviar(assunto, mensagem, configuracaoEmail, destinatario, null);
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
    }

    /**
     * Enviar email assincrono a partir dos parametros passados
     *
     * @param assunto Assunto do Email
     * @param mensagem Mensagem do Email
     * @param configuracaoEmail
     * @param destinatario Email de destino
     * @param anexos Anexos do email
     */
    @Asynchronous
    public void enviarAssincrono(String assunto, String mensagem, ConfiguracaoEmail configuracaoEmail, String destinatario, List<Attachment> anexos) {
        try {
            enviar(assunto, mensagem, configuracaoEmail, destinatario, anexos);
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
    }

    /**
     * Enviar email a partir dos parametros passados
     *
     * @param assunto Assunto do Email
     * @param mensagem Mensagem do Email
     * @param configuracaoEmail
     * @param destinatario Email de destino
     * @throws BusinessException
     */
    public void enviar(String assunto, String mensagem, ConfiguracaoEmail configuracaoEmail, String destinatario) throws BusinessException {
        enviar(assunto, mensagem, configuracaoEmail, destinatario, null);
    }

    /**
     * Enviar email a partir dos parametros passados
     *
     * @param assunto Assunto do Email
     * @param mensagem Mensagem do Email
     * @param configuracaoEmail
     * @param destinatario Email de destino
     * * @param anexos Anexos do email
     * @param anexos
     * @throws BusinessException
     */
    public void enviar(String assunto, String mensagem, ConfiguracaoEmail configuracaoEmail, String destinatario, List<Attachment> anexos) throws BusinessException {

        HtmlEmail email = new HtmlEmail();
        email.setHostName(configuracaoEmail.getHostName());
        try {

            for (String string : destinatario.split(",")) {
                if (string != null && !string.isEmpty()) {
                    email.addTo(string.trim());
                }
            }
            //anexo
            if (anexos != null) {
                for (Attachment anexo : anexos) {
                    email.attach(anexo.getDataSource(), anexo.getFileName(), anexo.getFileDescription());
                }
            }
            email.setCharset("UTF-8");
            email.setFrom(configuracaoEmail.getEmail(), configuracaoEmail.getNome());
            email.setSubject(assunto);
            email.setHtmlMsg(mensagem);
            email.setAuthentication(configuracaoEmail.getUsuario(), configuracaoEmail.getSenha());
            email.setSmtpPort(configuracaoEmail.getSmtpPort());
            email.setSSLOnConnect(configuracaoEmail.isSsl());
            email.setStartTLSEnabled(configuracaoEmail.isTls());

            email.send();

        } catch (EmailException ex) {
            erroSistemaBO.save(ex);
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            throw new BusinessException("Erro ao enviar o email. " + (ex.getMessage() != null ? ex.getMessage() : ""));
        }

    }

    static {
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDefaultEncoding("UTF-8");
    }

    public static String getMensagem(String layout, Map<String, Object> parametros) throws BusinessException {
        Template template;
        try {
            template = new Template("name", new StringReader(layout), cfg);
            StringWriter writer = new StringWriter();
            template.process(parametros, writer);
            writer.flush();
            writer.close();
            return writer.toString();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            throw new BusinessException("Erro ao pegar a mensagem de email. " + ex.getClass());
        }
    }

}
