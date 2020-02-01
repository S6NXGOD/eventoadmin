package br.com.eventoadmin.dao.controleacesso;

import br.com.eventoadmin.modelo.controleacesso.Perfil;
import br.com.eventoadmin.modelo.controleacesso.Permissao;
import br.com.eventoadmin.modelo.controleacesso.Usuario;
import com.xpert.persistence.dao.BaseDAO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ayslan
 */
@Local
public interface PermissaoDAO extends BaseDAO<Permissao> {

    /**
     * retorna todas as permissoes
     *
     * @return
     */
    public List<Permissao> getPermissoes();

    /**
     * retorna todas as permissoes
     *
     * @param apenasAtivas Indica se sao apenas as ativas
     * @return
     */
    public List<Permissao> getPermissoes(boolean apenasAtivas);

    /**
     * retorna as permissoes desse perfil
     *
     * @param perfil
     * @return
     */
    public List<Permissao> getPermissoes(Perfil perfil);

    /**
     * retorna as permissoes desse usuario
     *
     * @param usuario
     * @return
     */
    public List<Permissao> getPermissoes(Usuario usuario);

    /**
     * retorna as permissoes menu desse perfil
     *
     * @param perfil
     * @return
     */
    public List<Permissao> getPermissoesMenu(Perfil perfil);

    /**
     * retorn as permissoes de atalaho desse usuario
     *
     * @param usuario
     * @return
     */
    public List<Permissao> getPermissoesAtalhos(Usuario usuario);

    /**
     * retorn as permissoes desse usuario
     *
     * @param usuario
     * @param apenasAtivas Indica se sao apenas as ativas
     * @return
     */
    public List<Permissao> getPermissoes(Usuario usuario, boolean apenasAtivas);
}
