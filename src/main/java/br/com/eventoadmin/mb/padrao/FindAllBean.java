package br.com.eventoadmin.mb.padrao;

import br.com.eventoadmin.modelo.cadastro.Cidade;
import br.com.eventoadmin.modelo.cadastro.Estado;
import br.com.eventoadmin.modelo.cadastro.Pais;
import br.com.eventoadmin.modelo.controleacesso.Perfil;
import br.com.eventoadmin.modelo.controleacesso.Permissao;
import br.com.eventoadmin.modelo.controleacesso.SituacaoUsuario;
import br.com.eventoadmin.modelo.controleacesso.TipoRecuperacaoSenha;
import br.com.eventoadmin.modelo.controleacesso.Usuario;
import br.com.eventoadmin.modelo.email.TipoAssuntoEmail;
import br.com.eventoadmin.modelo.sede.Espaco;
import br.com.eventoadmin.modelo.sede.Sede;
import com.xpert.faces.bean.ClassModel;
import com.xpert.persistence.query.Restriction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author
 */
@ManagedBean
@ViewScoped
public class FindAllBean extends com.xpert.faces.bean.FindAllBean {

    private static final Map<Class, ClassModel> MODEL = new HashMap<Class, ClassModel>();

    static {
        put(Permissao.class, "descricao");
        put(Perfil.class, "descricao");
        put(Usuario.class, "nome");
        put(SituacaoUsuario.class, "descricao");
        put(TipoAssuntoEmail.class, "descricao");
        put(TipoRecuperacaoSenha.class, "descricao");
        put(Cidade.class, "nome");
        put(Estado.class, "sigla");
        put(Pais.class, "sigla");
        put(Sede.class, "nome");
        put(Espaco.class, "nome");
    }

    public static void put(Class entity, String order) {
        MODEL.put(entity, new ClassModel(order));
    }

    public static void put(Class entity, Restriction restriction, String order) {
        MODEL.put(entity, new ClassModel(order, restriction, order));
    }

    public static void put(Class entity, List<Restriction> restrictions, String order) {
        MODEL.put(entity, new ClassModel(order, restrictions, order));
    }

    @Override
    public Map<Class, ClassModel> getClassModel() {
        return MODEL;
    }
}
