package br.com.eventoadmin.modelo.sede;

import br.com.eventoadmin.modelo.cadastro.Cidade;
import br.com.eventoadmin.modelo.sede.enums.Scopo;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Joao Pedro
 */
@Entity
public class Sede implements Serializable {

    @Id
    @SequenceGenerator(name = "Sede", sequenceName = "sede_seq_id")
    @GeneratedValue(generator = "Sede")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Scopo scopo;

    @Size(max = 80)
    @NotBlank
    private String nome;

    @Size(max = 80)
    private String razaoSocial;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Cidade cidade;

    @Size(max = 150)
    private String endereco;

    @Size(max = 14)
    private String cnpj;

    @Size(max = 10)
    private String cep;

    @Size(max = 15)
    private String telefone;

    @Size(max = 15)
    private String whatsapp;

    @Size(max = 80)
    @Email
    private String email;

    @OrderBy("nome")
    @OneToMany(mappedBy = "sede")
    private List<Espaco> espacos;

    private boolean ativa = true;

    @Override
    public String toString() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Scopo getScopo() {
        return scopo;
    }

    public void setScopo(Scopo scopo) {
        this.scopo = scopo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Espaco> getEspacos() {
        return espacos;
    }

    public void setEspacos(List<Espaco> espacos) {
        this.espacos = espacos;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sede other = (Sede) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
