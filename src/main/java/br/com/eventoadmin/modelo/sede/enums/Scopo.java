package br.com.eventoadmin.modelo.sede.enums;

/**
 *
 * @author Joao Pedro
 */
public enum Scopo {
    Sede(1, "Sede de Evento"),
    Cliente(2, "Cliente"),
    Filial(3, "Filial");

    private Scopo(int num, String descricao) {
        this.descricao = descricao;
        this.num = num;
    }
    private String descricao;
    private int num;

    public String getDescricao() {
        return descricao;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
