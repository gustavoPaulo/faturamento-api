package com.g.Faturamento.model.enums;

public enum TipoFaturamento {

    RECEITA("Receita"),
    DESPESA("Despesa");

    private final String descricao;

    TipoFaturamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
