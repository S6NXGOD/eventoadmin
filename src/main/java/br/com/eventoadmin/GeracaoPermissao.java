package br.com.eventoadmin;

import br.com.eventoadmin.dao.controleacesso.PermissaoDAO;
import br.com.eventoadmin.modelo.controleacesso.Permissao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Classe para geracao das permissoes iniciais do sistema
 *
 * @author Ayslan
 */
@Stateless
public class GeracaoPermissao {

    private static final Logger logger = Logger.getLogger(GeracaoPermissao.class.getName());
    @EJB
    private PermissaoDAO permissaoDAO;

    public void generate() {

        /**
         * Administracao Sistema
         */
        create(new Permissao("administracaoSistema", "Sistema", true), null);


        /*
         * Controle de Acesso
         */
        create(new Permissao("controleAcesso", "Controle de Acesso", true), "administracaoSistema");

        //Permissao
        create(new Permissao("permissao", "Permissão", true), "controleAcesso");
        create(new Permissao("permissao.create", "Cadastro de Permissão", "/view/controleAcesso/permissao/createPermissao.jsf", true), "permissao");
        create(new Permissao("permissao.list", "Consulta de Permissão", "/view/controleAcesso/permissao/listPermissao.jsf", true), "permissao");
        create(new Permissao("permissao.audit", "Auditoria de Permissão"), "permissao");
        create(new Permissao("permissao.delete", "Exclusão de Permissão"), "permissao");
        create(new Permissao("permissao.ativacao", "Ativação de Permissão"), "permissao");
        create(new Permissao("permissao.inativacao", "Inativação de Permissão"), "permissao");

        //Usuario
        create(new Permissao("usuario", "Usuário", true), "controleAcesso");
        create(new Permissao("usuario.create", "Cadastro de Usuário", "/view/controleAcesso/usuario/createUsuario.jsf", true), "usuario");
        create(new Permissao("usuario.list", "Consulta de Usuário", "/view/controleAcesso/usuario/listUsuario.jsf", true), "usuario");
        create(new Permissao("usuario.audit", "Auditoria de Usuário"), "usuario");
        create(new Permissao("usuario.delete", "Exclusão de Usuário"), "usuario");

        //Perfil
        create(new Permissao("perfil", "Perfil", true), "controleAcesso");
        create(new Permissao("perfil.create", "Cadastro de Perfil", "/view/controleAcesso/perfil/createPerfil.jsf", true), "perfil");
        create(new Permissao("perfil.list", "Consulta de Perfil", "/view/controleAcesso/perfil/listPerfil.jsf", true), "perfil");
        create(new Permissao("perfil.audit", "Auditoria de Perfil"), "perfil");
        create(new Permissao("perfil.delete", "Exclusão de Perfil"), "perfil");

        //Acessos do Sistema
        create(new Permissao("acessoSistema.list", "Relatório de Acessos", "/view/controleAcesso/acessoSistema/listAcessoSistema.jsf", true), "controleAcesso");

        //Solicitacao recuperacao senha
        create(new Permissao("solicitacaoRecuperacaoSenha", "Recuperação de Senha", true), "controleAcesso");
        create(new Permissao("solicitacaoRecuperacaoSenha.list", "Consulta Recuperação de Senha", "/view/controleAcesso/solicitacaoRecuperacaoSenha/listSolicitacaoRecuperacaoSenha.jsf", true), "solicitacaoRecuperacaoSenha");
        create(new Permissao("solicitacaoRecuperacaoSenha.audit", "Auditoria de Recuperação de Senha"), "solicitacaoRecuperacaoSenha");

        /*
         * Email
         */
        create(new Permissao("email", "Email", true), "administracaoSistema");

        //Modelo email
        create(new Permissao("modeloEmail", "Modelo de Email", true), "email");
        create(new Permissao("modeloEmail.create", "Cadastro de Modelo de Email", "/view/email/modeloEmail/createModeloEmail.jsf", true), "modeloEmail");
        create(new Permissao("modeloEmail.list", "Consulta de Modelo de Email", "/view/email/modeloEmail/listModeloEmail.jsf", true), "modeloEmail");
        create(new Permissao("modeloEmail.audit", "Auditoria de Modelo de Email"), "modeloEmail");
        create(new Permissao("modeloEmail.delete", "Exclusão de Modelo de Email"), "modeloEmail");

        //Configuracao email
        create(new Permissao("configuracaoEmail", "Configuração de Email", true), "email");
        create(new Permissao("configuracaoEmail.create", "Cadastro de Configuração de Email", "/view/email/configuracaoEmail/createConfiguracaoEmail.jsf", true), "configuracaoEmail");
        create(new Permissao("configuracaoEmail.list", "Consulta de Configuração de Email", "/view/email/configuracaoEmail/listConfiguracaoEmail.jsf", true), "configuracaoEmail");
        create(new Permissao("configuracaoEmail.audit", "Auditoria de Configuração de Email"), "configuracaoEmail");
        create(new Permissao("configuracaoEmail.delete", "Exclusão de Configuração de Email"), "configuracaoEmail");

        create(new Permissao("cadastro", "Cadastro", true), null);
        create(new Permissao("basico", "Básico", true), "cadastro");

        //Cidade
        create(new Permissao("cidade", "Cidade", true), "basico");
        create(new Permissao("cidade.create", "Cadastro de Cidade", "/view/cidade/createCidade.jsf", true), "cidade");
        create(new Permissao("cidade.list", "Consulta de Cidade", "/view/cidade/listCidade.jsf", true), "cidade");
        create(new Permissao("cidade.audit", "Auditoria de Cidade"), "cidade");
        create(new Permissao("cidade.delete", "Exclusão de Cidade"), "cidade");

        //Estado
        create(new Permissao("estado", "Estado", true), "basico");
        create(new Permissao("estado.create", "Cadastro de Estado", "/view/estado/createEstado.jsf", true), "estado");
        create(new Permissao("estado.list", "Consulta de Estado", "/view/estado/listEstado.jsf", true), "estado");
        create(new Permissao("estado.audit", "Auditoria de Estado"), "estado");
        create(new Permissao("estado.delete", "Exclusão de Estado"), "estado");

        //Pais
        create(new Permissao("pais", "País", true), "basico");
        create(new Permissao("pais.create", "Cadastro de País", "/view/pais/createPais.jsf", true), "pais");
        create(new Permissao("pais.list", "Consulta de País", "/view/pais/listPais.jsf", true), "pais");
        create(new Permissao("pais.audit", "Auditoria de País"), "pais");
        create(new Permissao("pais.delete", "Exclusão de País"), "pais");

        //Espaco
        create(new Permissao("espaco", "Espaço", true), "cadastro");
        create(new Permissao("espaco.create", "Cadastro de Espaço", "/view/espaco/createEspaco.jsf", true), "espaco");
        create(new Permissao("espaco.list", "Consulta de Espaço", "/view/espaco/listEspaco.jsf", true), "espaco");
        create(new Permissao("espaco.audit", "Auditoria de Espaço"), "espaco");
        create(new Permissao("espaco.delete", "Exclusão de Espaço"), "espaco");

        //Sede
        create(new Permissao("sede", "Sede", true), "cadastro");
        create(new Permissao("sede.create", "Cadastro de Sede", "/view/sede/createSede.jsf", true), "sede");
        create(new Permissao("sede.list", "Consulta de Sede", "/view/sede/listSede.jsf", true), "sede");
        create(new Permissao("sede.audit", "Auditoria de Sede"), "sede");
        create(new Permissao("sede.delete", "Exclusão de Sede"), "sede");

        //Cliente
        create(new Permissao("cliente", "Cliente", true), "cadastro");
        create(new Permissao("cliente.create", "Cadastro de Cliente", "/view/cliente/createCliente.jsf", true), "cliente");
        create(new Permissao("cliente.list", "Consulta de Cliente", "/view/cliente/listCliente.jsf", true), "cliente");
        create(new Permissao("cliente.audit", "Auditoria de Cliente"), "cliente");
        create(new Permissao("cliente.delete", "Exclusão de Cliente"), "cliente");

        //Pacote
        create(new Permissao("pacote", "Pacote", true), "cadastro");
        create(new Permissao("pacote.create", "Cadastro de Pacote", "/view/pacote/createPacote.jsf", true), "pacote");
        create(new Permissao("pacote.list", "Consulta de Pacote", "/view/pacote/listPacote.jsf", true), "pacote");
        create(new Permissao("pacote.audit", "Auditoria de Pacote"), "pacote");
        create(new Permissao("pacote.delete", "Exclusão de Pacote"), "pacote");

        //Reserva
        create(new Permissao("reserva", "Reservas", true), null);
        create(new Permissao("reserva.create", "Cadastro de Reserva", "/view/reserva/createReserva.jsf", true), "reserva");
        create(new Permissao("reserva.list", "Consulta de Reserva", "/view/reserva/listReserva.jsf", true), "reserva");
        create(new Permissao("reserva.audit", "Auditoria de Reserva"), "reserva");
        create(new Permissao("reserva.delete", "Exclusão de Reserva"), "reserva");

        //Tipo Reserva
        create(new Permissao("tipoReserva", "Tipo Reserva", true), "cadastro");
        create(new Permissao("tipoReserva.create", "Cadastro de Tipo Reserva", "/view/tipoReserva/createTipoReserva.jsf", true), "tipoReserva");
        create(new Permissao("tipoReserva.list", "Consulta de Tipo Reserva", "/view/tipoReserva/listTipoReserva.jsf", true), "tipoReserva");
        create(new Permissao("tipoReserva.audit", "Auditoria de Tipo Reserva"), "tipoReserva");
        create(new Permissao("tipoReserva.delete", "Exclusão de Tipo Reserva"), "tipoReserva");

        /*
         * Configuracao
         */
        create(new Permissao("configuracaoSistema", "Configuração", true), "administracaoSistema");

        //Erro sistema
        create(new Permissao("erroSistema.list", "Relatório de Erros", "/view/configuracao/erroSistema/listErroSistema.jsf", true), "configuracaoSistema");

        /**
         * Permissoes Globais (essas permissoes todos terao acessos)
         */
        //Alterar Senha
        createGlobal(new Permissao("usuario.alterarSenha", "Alterar Senha", "/view/controleAcesso/password/alterPassword.jsf", true), "controleAcesso");
        createGlobal(new Permissao("usuario.ultimosAcessos", "Últimos Acessos", "/view/controleAcesso/acessoSistema/ultimosAcessoSistema.jsf", true), "controleAcesso");

    }

    private void createGlobal(Permissao permissao, String pai) {
        permissao.setGlobal(true);
        create(permissao, pai);
    }

    private void create(Permissao permissao, String pai) {

        if (pai != null && pai.equals(permissao.getKey())) {
            logger.log(Level.WARNING, "Permissao ''{0}'' pai dela mesmo. Esta permissao nao sera salva.", permissao.getKey());
            return;
        }

        Permissao permissaoDB = permissaoDAO.unique("key", permissao.getKey());

        if (pai != null && !pai.isEmpty()) {
            Permissao permissaoPai = permissaoDAO.unique("key", pai);
            if (permissaoPai == null) {
                logger.log(Level.WARNING, "Permissao ''{0}'' pai nao encontrada.", pai);
            }
            permissao.setPermissaoPai(permissaoPai);
        }

        if (permissaoDB != null) {
            permissaoDB.setUrl(permissao.getUrl());
            permissaoDB.setDescricao(permissao.getDescricao());
            permissaoDB.setPermissaoPai(permissao.getPermissaoPai());
            permissaoDB.setPossuiMenu(permissao.isPossuiMenu());
            permissaoDB.setGlobal(permissao.isGlobal());
            permissao = permissaoDB;
        }

        permissaoDAO.merge(permissao, false);
    }
}
