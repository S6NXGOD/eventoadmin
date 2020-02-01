package br.com.eventoadmin;

import br.com.eventoadmin.dao.DAO;
import br.com.eventoadmin.modelo.controleacesso.*;
import com.xpert.persistence.dao.BaseDAO;
import com.xpert.utils.Encryption;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Classe para geracao inicial dos dados do sistema, como perfil ADMINISTRADOR
 * usuario ADMIN, e outros
 *
 * @author Ayslan
 */
@Stateless
public class GeracaoDadosSistema {

    private static final Logger logger = Logger.getLogger(GeracaoDadosSistema.class.getName());
    @EJB
    private DAO dao;
    @EJB
    private GeracaoPermissao geracaoPermissao;
    @EJB
    private GeracaoModeloEmail geracaoModeloEmail;

    public <T> BaseDAO<T> getDAO(Class<T> entity) {
        return dao.getDAO(entity);
    }

    public void generate() {

        try {
            //gerar modelos de email
            geracaoModeloEmail.generate();
            //gerar permissões
            geracaoPermissao.generate();

            //gerar perfil ADMIN
            Perfil perfil = getDAO(Perfil.class).unique("descricao", "ADMINISTRADOR");
            if (perfil == null) {
                perfil = new Perfil();
                perfil.setDescricao("ADMINISTRADOR");
                perfil.setAtivo(true);
            }

            //adicionar todas as permissoes para o admin
            perfil.setPermissoes(getDAO(Permissao.class).listAll());
            if (perfil.getId() == null) {
                BaseDAO<Permissao> permissaoDAO = getDAO(Permissao.class);
                List<Permissao> atalhos = new ArrayList<Permissao>();
                //permissoes padrao para o perfil ADMIN
                atalhos.add(permissaoDAO.unique("key", "usuario.list"));
                atalhos.add(permissaoDAO.unique("key", "usuario.create"));
                atalhos.add(permissaoDAO.unique("key", "acessoSistema.list"));
                atalhos.add(permissaoDAO.unique("key", "usuario.alterarSenha"));
                atalhos.add(permissaoDAO.unique("key", "erroSistema.list"));
                atalhos.add(permissaoDAO.unique("key", "perfil.list"));
                perfil.setPermissoesAtalho(atalhos);
            }
            getDAO(Perfil.class).saveOrMerge(perfil, false);

            //criar usuario ADMIN
            Usuario usuario = getDAO(Usuario.class).unique("userLogin", "ADMIN");

            //se nao encontrou, criar um novo
            if (usuario == null) {
                //usuario
                usuario = new Usuario();
                usuario.setSituacaoUsuario(SituacaoUsuario.ATIVO);
                usuario.setMatricula("123");
                usuario.setRg("123");
                usuario.setCpf("12345678909");
                usuario.setEmail("admin@xpertsistemas.com.br");
                usuario.setNome("ADMINISTRADOR DO SISTEMA");
                List<Perfil> perfis = new ArrayList<Perfil>();
                perfis.add(perfil);
                usuario.setPerfis(perfis);
                usuario.setUserLogin("ADMIN");

                //definir senha "1" para o usuario
                try {
                    usuario.setUserPassword(Encryption.getSHA256("1"));
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }
                usuario.setSuperUsuario(true);
                getDAO(Usuario.class).saveOrMerge(usuario, false);

                //historico como ativo
                HistoricoSituacaoUsuario historicoSituacaoUsuario = new HistoricoSituacaoUsuario();
                historicoSituacaoUsuario.setDataSituacao(new Date());
                historicoSituacaoUsuario.setSituacaoUsuario(SituacaoUsuario.ATIVO);
                historicoSituacaoUsuario.setUsuario(usuario);
                getDAO(HistoricoSituacaoUsuario.class).saveOrMerge(historicoSituacaoUsuario, false);

            }

        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

}
