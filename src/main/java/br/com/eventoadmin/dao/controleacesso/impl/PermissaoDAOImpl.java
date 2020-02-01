package br.com.eventoadmin.dao.controleacesso.impl;

import br.com.eventoadmin.application.BaseDAOImpl;
import br.com.eventoadmin.dao.controleacesso.PermissaoDAO;
import br.com.eventoadmin.modelo.controleacesso.Perfil;
import br.com.eventoadmin.modelo.controleacesso.Permissao;
import br.com.eventoadmin.modelo.controleacesso.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Ayslan
 */
@Stateless
public class PermissaoDAOImpl extends BaseDAOImpl<Permissao> implements PermissaoDAO {

    @Override
    public Class getEntityClass() {
        return Permissao.class;
    }

    @Override
    public List<Permissao> getPermissoes() {
        return getPermissoes(false);
    }

    @Override
    public List<Permissao> getPermissoes(boolean apenasAtivas) {
        StringBuilder builder = new StringBuilder();
        //trazer todas permissoes
        builder.append("SELECT p FROM ").append(Permissao.class.getName()).append(" p ");
        if (apenasAtivas == true) {
            builder.append(" WHERE p.ativo = true ");
        }
        builder.append("ORDER BY p.descricao ");
        Query query = getEntityManager().createQuery(builder.toString());

        return query.getResultList();
    }

    @Override
    public List<Permissao> getPermissoes(Usuario usuario) {
        return getPermissoes(usuario, false);
    }

    @Override
    public List<Permissao> getPermissoes(Usuario usuario, boolean apenasAtivas) {
        StringBuilder builder = new StringBuilder();

        builder.append("SELECT DISTINCT p FROM ").append(Permissao.class.getName()).append(" p ");
        builder.append("LEFT JOIN p.perfis pe ");
        builder.append("LEFT JOIN pe.usuarios u ");
        builder.append("WHERE (u = :usuario or p.global = true) ");
        
        if (apenasAtivas == true) {
            builder.append(" AND (p.ativo = true) ");
        }
        builder.append("ORDER BY p.descricao ");

        Query query = getEntityManager().createQuery(builder.toString());
        query.setParameter("usuario", usuario);

        return query.getResultList();
    }

    @Override
    public List<Permissao> getPermissoes(Perfil perfil) {
        StringBuilder builder = new StringBuilder();

        if (perfil == null) {
            builder.append("SELECT DISTINCT p FROM ").append(Permissao.class.getName()).append(" p ");
            builder.append("LEFT JOIN FETCH p.permissaoPai ORDER BY p.descricao ");
        } else {
            builder.append("SELECT DISTINCT p FROM ").append(Perfil.class.getName()).append(" pu ");
            builder.append("JOIN pu.permissoes p LEFT JOIN FETCH p.permissaoPai WHERE pu = :perfil ORDER BY p.descricao ");
        }

        Query query = getEntityManager().createQuery(builder.toString());
        if (perfil != null) {
            query.setParameter("perfil", perfil);
        }

        return query.getResultList();
    }

    @Override
    public List<Permissao> getPermissoesMenu(Perfil perfil) {

        StringBuilder builder = new StringBuilder();
        builder.append("SELECT DISTINCT p FROM ").append(Perfil.class.getName()).append(" pu ");
        builder.append("JOIN pu.permissoesAtalho p LEFT JOIN FETCH p.permissaoPai WHERE pu = :perfil AND p.ativo = true ORDER BY p.descricao ");

        Query query = getEntityManager().createQuery(builder.toString());
        if (perfil != null) {
            query.setParameter("perfil", perfil);
        }

        return query.getResultList();
    }

    @Override
    public List<Permissao> getPermissoesAtalhos(Usuario usuario) {
        StringBuilder builder = new StringBuilder();

        builder.append("SELECT DISTINCT p FROM ").append(Usuario.class.getName()).append(" u ");
        builder.append("LEFT JOIN u.perfis pe INNER JOIN pe.permissoesAtalho p ");
        builder.append("WHERE u = :usuario AND p.url IS NOT NULL AND p.ativo = true ");
        builder.append("ORDER BY p.descricao ");

        Query query = getEntityManager().createQuery(builder.toString());
        query.setParameter("usuario", usuario);

        return query.getResultList();
    }
}
