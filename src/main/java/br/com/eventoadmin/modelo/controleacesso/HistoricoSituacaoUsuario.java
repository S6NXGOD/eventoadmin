package br.com.eventoadmin.modelo.controleacesso;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author ayslan
 */
@Entity
public class HistoricoSituacaoUsuario implements Serializable {

    @Id
    @SequenceGenerator(name = "HistoricoSituacaoUsuario", allocationSize = 1, sequenceName = "hist_sit_usuario_id_seq")
    @GeneratedValue(generator = "HistoricoSituacaoUsuario")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSituacao;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private SituacaoUsuario situacaoUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    /*
     * Usuario que alterou a situacao
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuarioAlteracao;

    public Usuario getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }

    public Date getDataSituacao() {
        return dataSituacao;
    }

    public void setDataSituacao(Date dataSituacao) {
        this.dataSituacao = dataSituacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SituacaoUsuario getSituacaoUsuario() {
        return situacaoUsuario;
    }

    public void setSituacaoUsuario(SituacaoUsuario situacaoUsuario) {
        this.situacaoUsuario = situacaoUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HistoricoSituacaoUsuario other = (HistoricoSituacaoUsuario) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

}
