package br.com.eventoadmin.modelo.controleacesso;

import com.xpert.i18n.I18N;

/**
 *
 * @author ayslan
 */
public enum SituacaoUsuario {
    
    ATIVO("situacaoUsuario.ativo"), 
    INATIVO("situacaoUsuario.inativo");

    private String descricao;

    private SituacaoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return I18N.get(descricao);
    }
    
}
