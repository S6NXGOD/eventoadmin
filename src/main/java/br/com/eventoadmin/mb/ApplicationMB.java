package br.com.eventoadmin.mb;

import br.com.eventoadmin.GeracaoDadosSistema;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Como esse managed bean tem o escopo de "Application" e a opcao "eager = true"
 * o metodo "@PostConstruct" sera chamado ao iniciar a aplicacao
 *
 * @author ayslan
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class ApplicationMB {

    @EJB
    private GeracaoDadosSistema geracaoDadosSistema;

    @PostConstruct
    public void init() {
        //gerar permissoes ao iniciar aplicacao
        geracaoDadosSistema.generate();
    }
}
