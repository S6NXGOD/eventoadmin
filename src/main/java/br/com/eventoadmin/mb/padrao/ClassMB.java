package br.com.eventoadmin.mb.padrao;

import br.com.eventoadmin.modelo.reserva.enums.tipoPagamento;
import br.com.eventoadmin.modelo.reserva.TipoReserva;
import br.com.eventoadmin.modelo.reserva.Reserva;
import br.com.eventoadmin.modelo.cliente.Cliente;
import br.com.eventoadmin.modelo.pacote.Pacote;
import br.com.eventoadmin.modelo.cadastro.Estado;
import br.com.eventoadmin.modelo.sede.Espaco;
import br.com.eventoadmin.modelo.cadastro.Pais;
import br.com.eventoadmin.modelo.sede.Sede;
import br.com.eventoadmin.modelo.sede.enums.Scopo;
import br.com.eventoadmin.modelo.cadastro.Cidade;
import br.com.eventoadmin.modelo.controleacesso.Permissao;
import br.com.eventoadmin.modelo.controleacesso.Usuario;
import br.com.eventoadmin.modelo.configuracao.ErroSistema;
import br.com.eventoadmin.modelo.controleacesso.SituacaoUsuario;
import br.com.eventoadmin.modelo.controleacesso.AcessoSistema;
import br.com.eventoadmin.modelo.email.ConfiguracaoEmail;
import br.com.eventoadmin.modelo.controleacesso.Perfil;
import br.com.eventoadmin.modelo.controleacesso.HistoricoSituacaoUsuario;
import br.com.eventoadmin.modelo.email.ModeloEmail;
import br.com.eventoadmin.modelo.controleacesso.SolicitacaoRecuperacaoSenha;
import br.com.eventoadmin.modelo.email.TipoAssuntoEmail;
import br.com.eventoadmin.modelo.controleacesso.TipoRecuperacaoSenha;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ClassMB {

    public Class getAcessoSistema() {
        return AcessoSistema.class;
    }

    public Class getErroSistema() {
        return ErroSistema.class;
    }

    public Class getHistoricoSituacaoUsuario() {
        return HistoricoSituacaoUsuario.class;
    }

    public Class getPerfil() {
        return Perfil.class;
    }

    public Class getPermissao() {
        return Permissao.class;
    }

    public Class getSituacaoUsuario() {
        return SituacaoUsuario.class;
    }

    public Class getUsuario() {
        return Usuario.class;
    }

    public Class getTipoAssuntoEmail() {
        return TipoAssuntoEmail.class;
    }

    public Class getModeloEmail() {
        return ModeloEmail.class;
    }

    public Class getSolicitacaoRecuperacaoSenha() {
        return SolicitacaoRecuperacaoSenha.class;
    }

    public Class getTipoRecuperacaoSenha() {
        return TipoRecuperacaoSenha.class;
    }

    public Class getConfiguracaoEmail() {
        return ConfiguracaoEmail.class;
    }

    public Class getEstado() {
        return Estado.class;
    }
    public Class getEspaco() {
        return Espaco.class;
    }
    public Class getPais() {
        return Pais.class;
    }
    public Class getSede() {
        return Sede.class;
    }
    public Class getScopo() {
        return Scopo.class;
    }
    public Class getCidade() {
        return Cidade.class;
    }

    public Class getCliente() {
        return Cliente.class;
    }
    public Class getPacote() {
        return Pacote.class;
    }

    public Class gettipoPagamento() {
        return tipoPagamento.class;
    }
    public Class getTipoReserva() {
        return TipoReserva.class;
    }
    public Class getReserva() {
        return Reserva.class;
    }

}