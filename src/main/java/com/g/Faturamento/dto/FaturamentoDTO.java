package com.g.Faturamento.dto;

public class FaturamentoDTO {

    private Integer codigo;
    private String tipo;
    private String valor;
    private String data;
    private String lancamento;
    private String descricao;

    public FaturamentoDTO(Integer codigo, String tipo, String valor, String data, String lancamento, String descricao) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
        this.lancamento = lancamento;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLancamento() {
        return lancamento;
    }

    public void setLancamento(String lancamento) {
        this.lancamento = lancamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
