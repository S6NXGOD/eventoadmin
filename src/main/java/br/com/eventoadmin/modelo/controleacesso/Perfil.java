package br.com.eventoadmin.modelo.controleacesso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Perfil implements Serializable {

    @Id
    @SequenceGenerator(name = "Perfil", allocationSize = 1, sequenceName = "perfil_id_seq")
    @GeneratedValue(generator = "Perfil")
    private Long id;

    @NotBlank
    private String descricao;
    private Boolean ativo = true;

    @ManyToMany(targetEntity = Permissao.class, fetch = FetchType.LAZY)
    @JoinTable(name = "perfil_permissao", joinColumns = @JoinColumn(name = "perfil_id"), inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    @OrderBy(value = "descricao")
    private List<Permissao> permissoes = new ArrayList<Permissao>();

    @ManyToMany(targetEntity = Permissao.class, fetch = FetchType.LAZY)
    @JoinTable(name = "perfil_permissao_menu", joinColumns = @JoinColumn(name = "perfil_id"), inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    @OrderBy(value = "descricao")
    private List<Permissao> permissoesAtalho = new ArrayList<Permissao>();

    @OrderBy("nome")
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "perfis")
    private List<Usuario> usuarios;

    public Perfil() {
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao != null) {
            descricao = descricao.trim().toUpperCase();
        }
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public List<Permissao> getPermissoesAtalho() {
        return permissoesAtalho;
    }

    public void setPermissoesAtalho(List<Permissao> permissoesAtalho) {
        this.permissoesAtalho = permissoesAtalho;
    }

    @Override
    public String toString() {
        return descricao;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Perfil other = (Perfil) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
