package br.com.eventoadmin.modelo.audit;

import br.com.eventoadmin.modelo.controleacesso.Usuario;
import com.xpert.audit.model.AbstractAuditing;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author
 */
@Entity
public class Auditing extends AbstractAuditing implements Serializable {

    @Id
    @SequenceGenerator(name = "Auditing", allocationSize = 1, sequenceName = "auditing_id_seq")
    @GeneratedValue(generator = "Auditing")
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auditing")
    private List<Metadata> metadatas;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public List getMetadatas() {
        return metadatas;
    }

    @Override
    public void setMetadatas(List metadatas) {
        this.metadatas = metadatas;
    }

    @Override
    public String getUserName() {
        if (usuario != null) {
            return usuario.getNome();
        }
        return null;
    }
}
