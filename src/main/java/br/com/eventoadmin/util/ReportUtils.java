package br.com.eventoadmin.util;

import br.com.eventoadmin.modelo.controleacesso.Usuario;
import com.xpert.faces.utils.FacesJasper;
import com.xpert.faces.utils.FacesUtils;
import com.xpert.i18n.I18N;
import com.xpert.jasper.JRBeanCollectionDataSource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;
import javax.persistence.EntityManager;
import net.sf.jasperreports.engine.JRParameter;

/**
 *
 * @author ayslan
 */
public class ReportUtils {

    private static final Logger logger = Logger.getLogger(ReportUtils.class.getName());
    public static final String LOGO = "/images/logo.png";
    public static final String TITULO = "SISTEMA [eventoadmin]";
    public static final String SUBTITULO = "Subtítulo do Sistema [eventoadmin]";
    public static final String TEMPLATE_RETRATO_PADRAO = "/WEB-INF/report/template/TemplateRetrato.jasper";
    public static final String TEMPLATE_PAISAGEM_PADRAO = "/WEB-INF/report/template/TemplatePaisagem.jasper";
    public static final String REPORT_PATH = "REPORT_PATH";
    public static final String NOME_EMPRESA = "Nome de Empresa de Teste";
    public static final String CNPJ_EMPRESA = "00.000.000/0000-00";

    /**
     * Cria um relatorio com layout retrato a partir do template padrao
     *
     * @param dataSource lista de objetos que criarao o data source do relatorio
     * @param path caminho do relatorio
     * @param fileName Nome do Arquivo gerado
     */
    public static void createJasperReportPortrait(List dataSource, String path, String fileName) {
        createJasperReportPortrait(dataSource, null, path, fileName, null);
    }

    /**
     * Cria um relatorio com layout retrato a partir do template padrao
     *
     * @param dataSource lista de objetos que criarao o data source do relatorio
     * @param parameters map de parametros a serem adicionados no relatorio
     * @param path caminho do relatorio
     * @param fileName Nome do Arquivo gerado
     */
    public static void createJasperReportPortrait(List dataSource, Map parameters, String path, String fileName) {
        createJasperReportPortrait(dataSource, parameters, path, fileName, null);
    }

    /**
     * Cria um relatorio com layout retrato a partir do template padrao
     *
     * @param dataSource lista de objetos que criarao o data source do relatorio
     * @param parameters map de parametros a serem adicionados no relatorio
     * @param path caminho do relatorio
     * @param fileName Nome do Arquivo gerado
     * @param entityManager
     */
    public static void createJasperReportPortrait(List dataSource, Map parameters, String path, String fileName, EntityManager entityManager) {
        createJasperReport(dataSource, parameters, path, fileName, TEMPLATE_RETRATO_PADRAO, entityManager);
    }

    /**
     * Cria um relatorio com layout paisagem a partir do template padrao
     *
     * @param dataSource lista de objetos que criarao o data source do relatorio
     * @param path caminho do relatorio
     * @param fileName Nome do Arquivo gerado
     */
    public static void createJasperReportLandscape(List dataSource, String path, String fileName) {
        createJasperReportLandscape(dataSource, null, path, fileName, null);
    }

    /**
     * Cria um relatorio com layout paisagem a partir do template padrao
     *
     * @param dataSource lista de objetos que criarao o data source do relatorio
     * @param parameters map de parametros a serem adicionados no relatorio
     * @param path caminho do relatorio
     * @param fileName Nome do Arquivo gerado
     */
    public static void createJasperReportLandscape(List dataSource, Map parameters, String path, String fileName) {
        createJasperReportLandscape(dataSource, parameters, path, fileName, null);
    }

    /**
     * Cria um relatorio com layout paisagem a partir do template padrao
     *
     * @param dataSource lista de objetos que criarao o data source do relatorio
     * @param parameters map de parametros a serem adicionados no relatorio
     * @param path caminho do relatorio
     * @param fileName Nome do Arquivo gerado
     * @param entityManager
     */
    public static void createJasperReportLandscape(List dataSource, Map parameters, String path, String fileName, EntityManager entityManager) {
        createJasperReport(dataSource, parameters, path, fileName, TEMPLATE_PAISAGEM_PADRAO, entityManager);
    }

    /**
     * Cria um relatorio
     *
     * @param dataSource lista de objetos que criarao o data source do relatorio
     * @param path caminho do relatorio
     * @param fileName Nome do Arquivo gerado
     */
    public static void createJasperReport(List dataSource, String path, String fileName) {
        createJasperReport(dataSource, null, path, fileName, null, null);
    }

    /**
     * Cria um relatorio
     *
     * @param dataSource lista de objetos que criarao o data source do relatorio
     * @param parameters map de parametros a serem adicionados no relatorio
     * @param path caminho do relatorio
     * @param fileName Nome do Arquivo gerado
     */
    public static void createJasperReport(List dataSource, Map parameters, String path, String fileName) {
        createJasperReport(dataSource, parameters, path, fileName, null, null);
    }

    /**
     * Cria um relatorio
     *
     * @param dataSource lista de objetos que criarao o data source do relatorio
     * @param parameters map de parametros a serem adicionados no relatorio
     * @param path caminho do relatorio
     * @param fileName Nome do Arquivo gerado
     * @param entityManager
     */
    public static void createJasperReport(List dataSource, Map parameters, String path, String fileName, EntityManager entityManager) {
        createJasperReport(dataSource, parameters, path, fileName, null, entityManager);
    }

    /**
     * Cria um relatorio
     *
     * @param dataSource lista de objetos que criarao o data source do relatorio
     * @param path caminho do relatorio
     * @param fileName Nome do Arquivo gerado
     * @param template Caminho do template (ao informar o template o reltorio
     * sera inserido como subrelatorio do template)
     */
    public static void createJasperReport(List dataSource, String path, String fileName, String template) {
        createJasperReport(dataSource, null, path, fileName, template, null);
    }

    /**
     * Cria um relatorio
     *
     * @param dataSource lista de objetos que criarao o data source do relatorio
     * @param parameters map de parametros a serem adicionados no relatorio
     * @param path caminho do relatorio
     * @param fileName Nome do Arquivo gerado
     * @param template Caminho do template (ao informar o template o reltorio
     * sera inserido como subrelatorio do template)
     */
    public static void createJasperReport(List dataSource, Map parameters, String path, String fileName, String template) {
        createJasperReport(dataSource, parameters, path, fileName, template, null);
    }

    /**
     * Cria um relatorio
     *
     * @param dataSource lista de objetos que criarao o data source do relatorio
     * @param parameters map de parametros a serem adicionados no relatorio
     * @param path caminho do relatorio
     * @param fileName Nome do Arquivo gerado
     * @param template Caminho do template (ao informar o template o reltorio
     * sera inserido como subrelatorio do template)
     * @param entityManager
     */
    public static void createJasperReport(List dataSource, Map parameters, String path, String fileName, String template, EntityManager entityManager) {
        Usuario usuario = SessaoUtils.getUser();
        createJasperReport(dataSource, parameters, path, fileName, template, usuario, entityManager);
    }

    /**
     * Cria um relatorio
     *
     * @param dataSource lista de objetos que criarao o data source do relatorio
     * @param parameters map de parametros a serem adicionados no relatorio
     * @param path caminho do relatorio
     * @param fileName Nome do Arquivo gerado
     * @param template Caminho do template (ao informar o template o reltorio
     * sera inserido como subrelatorio do template)
     * @param usuario usuario que sera passado por parametro ao relatorio
     * @param entityManager
     */
    public static void createJasperReport(List dataSource, Map parameters, String path, String fileName, String template,
            Usuario usuario, EntityManager entityManager) {
        PartialViewContext partialViewContext = FacesContext.getCurrentInstance().getPartialViewContext();

        //verificar se eh requisicao ajax, caso seja, informar warning
        if (partialViewContext != null && partialViewContext.isAjaxRequest()) {
            logger.warning("Relatorio sendo gerado a partir de uma requisicao ajax. Uma requisicao Ajax nao gera download de arquivos, para isso coloque ajax='false'.");
        }

        if (parameters == null) {
            parameters = new HashMap();
        }

        String logo = FacesContext.getCurrentInstance().getExternalContext().getRealPath(LOGO);
        if (new File(logo).exists()) {
            parameters.put("logo", logo);
        } else {
            logger.warning("Logo do nao encontrado no caminho " + LOGO);
            parameters.put("logo", null);
        }

        //parametros padrao do relatorio
        parameters.put(REPORT_PATH, FacesContext.getCurrentInstance().getExternalContext().getRealPath(path));
        parameters.put("usuario", usuario.getNome());
        parameters.put("ip", FacesUtils.getIP());
        parameters.put(JRParameter.REPORT_LOCALE, I18N.getLocale());
        parameters.put("titulo", TITULO);
        parameters.put("subtitulo", SUBTITULO);

        try {
            //criar do template deve ser inserido o data source nos parametros
            if (template != null && !template.trim().isEmpty()) {
                parameters.put("REPORT_DATA_SOURCE_CLONE", new JRBeanCollectionDataSource(dataSource, entityManager));
                FacesJasper.createJasperReport(null, parameters, template, fileName, entityManager);
            } else {
                FacesJasper.createJasperReport(dataSource, parameters, path, fileName, entityManager);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
