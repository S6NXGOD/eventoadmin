package br.com.eventoadmin.modelo.email;

import br.com.eventoadmin.constante.Constantes;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ayslan
 */
@Entity
public class ModeloEmail implements Serializable {

    @Id
    @SequenceGenerator(name = "ModeloEmail", allocationSize = 1, sequenceName = "modeloemail_id_seq")
    @GeneratedValue(generator = "ModeloEmail")
    private Long id;

    @Column(columnDefinition = Constantes.TIPO_TEXTO_BANCO)
    private String layout;

    @NotBlank
    private String assunto;

    @NotNull
    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private TipoAssuntoEmail tipoAssuntoEmail;

    @NotNull
    @ManyToOne
    private ConfiguracaoEmail configuracaoEmail;


    public ConfiguracaoEmail getConfiguracaoEmail() {
        return configuracaoEmail;
    }

    public void setConfiguracaoEmail(ConfiguracaoEmail configuracaoEmail) {
        this.configuracaoEmail = configuracaoEmail;
    }

    public TipoAssuntoEmail getTipoAssuntoEmail() {
        return tipoAssuntoEmail;
    }

    public void setTipoAssuntoEmail(TipoAssuntoEmail tipoAssuntoEmail) {
        this.tipoAssuntoEmail = tipoAssuntoEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    @Override
    public String toString() {
        return assunto;
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
        final ModeloEmail other = (ModeloEmail) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
