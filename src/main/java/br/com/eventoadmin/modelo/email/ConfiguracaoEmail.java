package br.com.eventoadmin.modelo.email;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ayslan
 */
@Entity
public class ConfiguracaoEmail implements Serializable {

    @Id
    @SequenceGenerator(name = "ConfiguracaoEmail", allocationSize = 1, sequenceName = "configuracaoemail_id_seq")
    @GeneratedValue(generator = "ConfiguracaoEmail")
    private Long id;

    @Email
    @NotBlank
    private String email;
    
    @NotBlank
    private String usuario;

    @NotBlank
    private String nome;

    @NotBlank
    private String senha;

    @NotNull
    private Integer smtpPort;

    @Column(name = "is_tsl")
    private boolean tls;

    @Column(name = "is_ssl")
    private boolean ssl;

    @NotBlank
    private String hostName;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(Integer smtpPort) {
        this.smtpPort = smtpPort;
    }

    public boolean isTls() {
        return tls;
    }

    public void setTls(boolean tls) {
        this.tls = tls;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final ConfiguracaoEmail other = (ConfiguracaoEmail) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return email + ". SMTP Port=" + smtpPort + ", TLS=" + tls + ", SSL=" + ssl + ", Host Name=" + hostName;
    }

}
