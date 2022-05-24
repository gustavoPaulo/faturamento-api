package com.g.Faturamento.model;

import com.g.Faturamento.model.enums.TipoFaturamento;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Faturamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;

    @NotNull(message = "Tipo é obrigatório!")
    private TipoFaturamento tipo;

    @NotNull(message = "Valor é obrigatório!")
    @Size(min = 0, message = "Valor deve ser maior que 0,01")
    private double valor;

    @NotNull(message = "Data é obrigatória!")
    private Date data;

    private Date lancamento;
    private String descricao;

    public Faturamento() {}

    public Faturamento(Integer codigo, TipoFaturamento tipo, double valor, Date data, Date lancamento, String descricao) {
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

    public TipoFaturamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoFaturamento tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getLancamento() {
        return lancamento;
    }

    public void setLancamento(Date lancamento) {
        this.lancamento = lancamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faturamento that = (Faturamento) o;
        return codigo.equals(that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
