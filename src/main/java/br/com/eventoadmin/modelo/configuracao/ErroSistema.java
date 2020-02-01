package br.com.eventoadmin.modelo.configuracao;

import br.com.eventoadmin.constante.Constantes;
import br.com.eventoadmin.modelo.controleacesso.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author ayslan
 */
@Entity
public class ErroSistema implements Serializable {

    @Id
    @SequenceGenerator(name = "ErroSistema", allocationSize = 1, sequenceName = "errosistema_id_seq")
    @GeneratedValue(generator = "ErroSistema")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @Column(length = 500)
    private String informacaoNavegador;

    @Column(columnDefinition = Constantes.TIPO_TEXTO_BANCO)
    private String pilhaErro;

    @Column(length = 190)
    private String funcionalidade;

    @Column(length = 500)
    private String url;
    @ManyToOne
    private Usuario usuario;

    private static final String PACOTE_PADRAO_SISTEMA = "br.com.eventoadmin";

    public String getPilhaErroFormatada() {
        if (pilhaErro != null && pilhaErro.length() > 60) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new StringReader(pilhaErro));
                String linha;
                StringBuilder stringBuilder = new StringBuilder();
                while ((linha = bufferedReader.readLine()) != null) {
                    if (linha.contains(PACOTE_PADRAO_SISTEMA)) {
                        stringBuilder.append("<span style='background: #EEDD82'>").append(linha.replace(PACOTE_PADRAO_SISTEMA, "<b>" + PACOTE_PADRAO_SISTEMA + "</b>")).append("</span>");
                    } else {
                        stringBuilder.append(linha);
                    }
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } catch (IOException ex) {
                return pilhaErro;
            }
        }
        return pilhaErro;
    }

    public String getResumo() {
        if (pilhaErro != null && pilhaErro.length() > 60) {
            return pilhaErro.substring(0, 59) + "...";
        }
        return pilhaErro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInformacaoNavegador() {
        return informacaoNavegador;
    }

    public void setInformacaoNavegador(String informacaoNavegador) {
        this.informacaoNavegador = informacaoNavegador;
    }

    public String getPilhaErro() {
        return pilhaErro;
    }

    public void setPilhaErro(String pilhaErro) {
        this.pilhaErro = pilhaErro;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFuncionalidade() {
        return funcionalidade;
    }

    public void setFuncionalidade(String funcionalidade) {
        this.funcionalidade = funcionalidade;
    }
}
