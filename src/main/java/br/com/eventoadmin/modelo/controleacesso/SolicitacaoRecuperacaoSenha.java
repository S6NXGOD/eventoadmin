package br.com.eventoadmin.modelo.controleacesso;

import br.com.eventoadmin.constante.ConstantesURL;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author ayslan
 */
@Entity
public class SolicitacaoRecuperacaoSenha implements Serializable {

    @Id
    @SequenceGenerator(name = "SolicitacaoRecuperacaoSenha", allocationSize = 1, sequenceName = "solicitrecuperacaosenha_id_seq")
    @GeneratedValue(generator = "SolicitacaoRecuperacaoSenha")
    private Long id;
    
    @Email
    private String email;
    private String token;
    private boolean ativo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataValidade;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracaoSenha;

    @ManyToOne
    private Usuario usuario;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private TipoRecuperacaoSenha tipoRecuperacaoSenha;

    private static final SimpleDateFormat FORMAT_DATA_HORA_MINUTO = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public String getUrlRecuperacaoSenha() {
        return ConstantesURL.URL_APLICACAO + "/auth/recuperacao-senha?tk=" + token + "&email=" + email;
    }

    public String getDataValidadeFormatada() {
        if (dataValidade != null) {
            return FORMAT_DATA_HORA_MINUTO.format(dataValidade);
        }
        return null;
    }

    public boolean isDataNaValidade() {
        if (ativo == true) {
            if (dataValidade == null || new Date().before(dataValidade)) {
                return true;
            }
        }
        return false;
    }

    public TipoRecuperacaoSenha getTipoRecuperacaoSenha() {
        return tipoRecuperacaoSenha;
    }

    public void setTipoRecuperacaoSenha(TipoRecuperacaoSenha tipoRecuperacaoSenha) {
        this.tipoRecuperacaoSenha = tipoRecuperacaoSenha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null) {
            email = email.trim().toLowerCase();
        }
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Date getDataAlteracaoSenha() {
        return dataAlteracaoSenha;
    }

    public void setDataAlteracaoSenha(Date dataAlteracaoSenha) {
        this.dataAlteracaoSenha = dataAlteracaoSenha;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SolicitacaoRecuperacaoSenha other = (SolicitacaoRecuperacaoSenha) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
