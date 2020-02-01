package br.com.eventoadmin.modelo.controleacesso;

import com.xpert.audit.NotAudited;
import com.xpert.security.model.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Usuario implements Serializable, User {

    @Id
    @SequenceGenerator(name = "Usuario", allocationSize = 1, sequenceName = "usuario_id_seq")
    @GeneratedValue(generator = "Usuario")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @NotBlank
    private String nome;

    @Size(max = 16)
    @NotBlank
    private String cpf;

    @NotBlank
    @Size(max = 150)
    @Email
    private String email;

    @Size(max = 50)
    private String matricula;

    @Size(max = 230)
    private String rg;

    @NotBlank
    @Size(min = 4)
    private String userLogin;

    @NotAudited
    @Size(min = 5)
    private String userPassword;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private SituacaoUsuario situacaoUsuario;

    @OrderBy(value = "descricao")
    @ManyToMany(targetEntity = Perfil.class, fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_perfil", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private List<Perfil> perfis;

    @NotAudited
    @OrderBy("dataSituacao DESC")
    @OneToMany(mappedBy = "usuario")
    private List<HistoricoSituacaoUsuario> historicosSituacao;

    private boolean superUsuario;

    private Boolean emailCadastroEnviado;

    private Boolean senhaCadastrada;

    public Usuario() {
        senhaCadastrada = false;
        emailCadastroEnviado = false;
        situacaoUsuario = SituacaoUsuario.ATIVO;
        historicosSituacao = new ArrayList<HistoricoSituacaoUsuario>();
    }

    @Override
    public boolean isActive() {
        return situacaoUsuario != null && situacaoUsuario.equals(SituacaoUsuario.ATIVO);
    }

    public Boolean getSenhaCadastrada() {
        if (senhaCadastrada == null) {
            senhaCadastrada = false;
        }
        return senhaCadastrada;
    }

    public void setSenhaCadastrada(Boolean senhaCadastrada) {
        this.senhaCadastrada = senhaCadastrada;
    }

    public Boolean getEmailCadastroEnviado() {
        if (emailCadastroEnviado == null) {
            emailCadastroEnviado = false;
        }
        return emailCadastroEnviado;
    }

    public void setEmailCadastroEnviado(Boolean emailCadastroEnviado) {
        this.emailCadastroEnviado = emailCadastroEnviado;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public List<HistoricoSituacaoUsuario> getHistoricosSituacao() {
        return historicosSituacao;
    }

    public void setHistoricosSituacao(List<HistoricoSituacaoUsuario> historicosSituacao) {
        this.historicosSituacao = historicosSituacao;
    }

    public SituacaoUsuario getSituacaoUsuario() {
        return situacaoUsuario;
    }

    public void setSituacaoUsuario(SituacaoUsuario situacaoUsuario) {
        this.situacaoUsuario = situacaoUsuario;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSuperUsuario() {
        return superUsuario;
    }

    public void setSuperUsuario(boolean superUsuario) {
        this.superUsuario = superUsuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null) {
            nome = nome.trim().toUpperCase();
        }
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    @Override
    public String getUserLogin() {
        return userLogin;
    }

    @Override
    public void setUserLogin(String userLogin) {
        if (userLogin != null) {
            userLogin = userLogin.trim().toUpperCase();
        }
        this.userLogin = userLogin;
    }

    @Override
    public String getUserPassword() {
        return userPassword;
    }

    @Override
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public void setActive(boolean active) {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Usuario other = (Usuario) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    

    @Override
    public String toString() {
        return getNome();
    }
}
