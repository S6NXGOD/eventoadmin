/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eventoadmin.modelo.reserva.enums;

/**
 *
 * @author Joao Pedro
 */
public enum tipoPagamento {
    item1(1, "Dinheiro"),
    item2(2, "Boleto"),
    item3(3, "Cartão"),
    item4(4, "Outros");

    private tipoPagamento(int num, String descricao) {
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
