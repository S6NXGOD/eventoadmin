package br.com.eventoadmin.modelo.controleacesso;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ayslan
 */
@Entity
public class AcessoSistema implements Serializable {

    @Id
    @SequenceGenerator(name = "AcessoSistema", allocationSize = 1, sequenceName = "acessosistema_id_seq")
    @GeneratedValue(generator = "AcessoSistema")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;

    private String ip;

    private String userAgent;

    @ManyToOne
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

}
