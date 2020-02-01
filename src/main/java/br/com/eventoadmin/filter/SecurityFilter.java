package br.com.eventoadmin.filter;

import com.xpert.security.filter.AbstractSecurityFilter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;

/**
 * Filtro para controle de acesso de usuarios
 *
 * @author Ayslan
 */
@WebFilter(filterName = "SecurityFilter", urlPatterns = {SecurityFilter.PATTERN})
public class SecurityFilter extends AbstractSecurityFilter {

    private static final Logger logger = Logger.getLogger(SecurityFilter.class.getName());
    /**
     * tudo que estiver dentro desta pasta necessita de controle de acesso
     */
    public static final String PATTERN = "/view/*";
    private static final String HOME = "/index.jsf";
    /**
     * lista de URLs a serem ignoradas pelo controle de acesso
     */
    private static final String[] IGNORE_URL = {"/view/home.jsf"};

    @Override
    public String getUserSessionName() {
        return "sessaoUsuarioMB";
    }

    @Override
    public String getHomePage() {
        return HOME;
    }

    @Override
    public String[] getIgnoredUrls() {
        return IGNORE_URL;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.log(Level.INFO, "Iniciando Filter SecurityFilter para url: {0}", PATTERN);
    }

    @Override
    public void destroy() {
    }
}
