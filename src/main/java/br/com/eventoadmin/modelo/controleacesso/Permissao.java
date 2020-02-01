package br.com.eventoadmin.modelo.controleacesso;

import com.xpert.security.model.Role;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Permissao implements Serializable, Role {

    @Id
    @SequenceGenerator(name = "Permissao", allocationSize = 1, sequenceName = "Permissao_id_seq")
    @GeneratedValue(generator = "Permissao")
    private Long id;
    /**
     * nome da permissao
     */
    @NotBlank
    private String descricao;

    @Column(name = "chave")
    @NotBlank
    private String key;

    /**
     * urls permitidas a acesso desta permissao
     */
    @Size(max = 1000)
    @Column
    private String url;

    /**
     * url a ser chamada no menu
     */
    private String urlMenu;
    /**
     * nome a ser exibido no menu
     */
    private String nomeMenu;

    private boolean possuiMenu = false;

    private boolean global = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private Permissao permissaoPai;

    @OrderBy(value = "descricao")
    @OneToMany(mappedBy = "permissaoPai")
    private List<Permissao> permissoesFilhas = new ArrayList<Permissao>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissoes")
    private List<Perfil> perfis;
    
    private boolean ativo = true;

    @Transient
    private String caminhoPermissao;
    
    /**
     * iniciar com valor padrao 0
     */
    private Integer ordenacao = 0;

    public Permissao() {
    }

    public Permissao(String key, String descricao, String url) {
        this.descricao = descricao;
        this.key = key;
        this.url = url;
    }

    public Permissao(String key, String descricao, String url, boolean possuiMenu) {
        this.descricao = descricao;
        this.key = key;
        this.url = url;
        this.possuiMenu = possuiMenu;
    }

    public Permissao(String key, String descricao, boolean possuiMenu) {
        this.descricao = descricao;
        this.key = key;
        this.possuiMenu = possuiMenu;
    }

    public Permissao(String key, String descricao) {
        this.descricao = descricao;
        this.key = key;
    }

    /**
     * Caso possua urlMenu informada esta sera retornada, senão caso a url seja
     * informada retornar a primeira url encontrada (podem ser varias urls
     * separadas por virgula)
     *
     * @return
     */
    public String getUrlMenuVerificado() {
        if (urlMenu != null && !urlMenu.trim().isEmpty()) {
            return urlMenu;
        }
        if (url != null && !url.trim().isEmpty()) {
            return url.split(",")[0];
        }
        return null;
    }

    /**
     * Caso possua nomeMenu informada este sera retornado, senão retorna a
     * descricao da permissao
     *
     * @return
     */
    public String getNomeMenuVerificado() {
        if (nomeMenu != null && !nomeMenu.trim().isEmpty()) {
            return nomeMenu;
        }
        return descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getOrdenacao() {
        return ordenacao;
    }

    public void setOrdenacao(Integer ordenacao) {
        this.ordenacao = ordenacao;
    }
    
    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public String getNomeMenu() {
        return nomeMenu;
    }

    public void setNomeMenu(String nomeMenu) {
        this.nomeMenu = nomeMenu;
    }

    public boolean isPossuiMenu() {
        return possuiMenu;
    }

    public void setPossuiMenu(boolean possuiMenu) {
        this.possuiMenu = possuiMenu;
    }

    public String getUrlMenu() {
        return urlMenu;
    }

    public void setUrlMenu(String urlMenu) {
        this.urlMenu = urlMenu;
    }

    public Permissao getPermissaoPai() {
        return permissaoPai;
    }

    public void setPermissaoPai(Permissao permissaoPai) {
        this.permissaoPai = permissaoPai;
    }

    public List<Permissao> getPermissoesFilhas() {
        return permissoesFilhas;
    }

    public void setPermissoesFilhas(List<Permissao> permissoesFilhas) {
        this.permissoesFilhas = permissoesFilhas;
    }
    

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    public String getCaminhoPermissao() {
        return caminhoPermissao;
    }

    public void setCaminhoPermissao(String caminhoPermissao) {
        this.caminhoPermissao = caminhoPermissao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return key;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Permissao other = (Permissao) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
