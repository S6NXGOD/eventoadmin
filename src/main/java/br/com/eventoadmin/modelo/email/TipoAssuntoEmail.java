package br.com.eventoadmin.modelo.email;

/**
 *
 * @author ayslan
 */
public enum TipoAssuntoEmail {

    RECUPERACAO_SENHA("Recuperação de Senha"),
    NOVO_USUARIO_SISTEMA("Novo Usuário no Sistema");

    private TipoAssuntoEmail(String descricao) {
        this.descricao = descricao;
    }
    private String descricao;

    public String getDescricao() {
        return descricao;
    }
}
