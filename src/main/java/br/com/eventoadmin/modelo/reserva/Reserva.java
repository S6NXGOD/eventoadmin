package br.com.eventoadmin.modelo.reserva;

import br.com.eventoadmin.modelo.cliente.Cliente;
import br.com.eventoadmin.modelo.pacote.Pacote;
import br.com.eventoadmin.modelo.reserva.enums.tipoPagamento;
import br.com.eventoadmin.modelo.sede.Espaco;
import br.com.eventoadmin.modelo.sede.Sede;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Joao Pedro
 */
@Entity
public class Reserva implements Serializable {

    @Id
    @SequenceGenerator(name = "Reserva", sequenceName = "reserva_seq_id")
    @GeneratedValue(generator = "Reserva")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Sede sede;

    @ManyToOne(fetch = FetchType.LAZY)
    private Espaco espaco;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pacote pacote;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date checkin;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date checkout;

    @ManyToOne(fetch = FetchType.LAZY)
    private TipoReserva tiporeserva;

    @Size(max = 150)
    private String observacao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private tipoPagamento tipopagamento;

    private BigDecimal valorpago;

    private BigDecimal valorReserva;

    private boolean pagamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public Espaco getEspaco() {
        return espaco;
    }

    public void setEspaco(Espaco espaco) {
        this.espaco = espaco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public TipoReserva getTiporeserva() {
        return tiporeserva;
    }

    public void setTiporeserva(TipoReserva tiporeserva) {
        this.tiporeserva = tiporeserva;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public tipoPagamento getTipopagamento() {
        return tipopagamento;
    }

    public void setTipopagamento(tipoPagamento tipopagamento) {
        this.tipopagamento = tipopagamento;
    }

    public BigDecimal getValorpago() {
        return valorpago;
    }

    public void setValorpago(BigDecimal valorpago) {
        this.valorpago = valorpago;
    }

    public BigDecimal getValorReserva() {
        return valorReserva;
    }

    public void setValorReserva(BigDecimal valorReserva) {
        this.valorReserva = valorReserva;
    }

    public boolean isPagamento() {
        return pagamento;
    }

    public void setPagamento(boolean pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Reserva other = (Reserva) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
