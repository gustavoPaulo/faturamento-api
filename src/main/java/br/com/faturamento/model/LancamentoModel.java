package br.com.faturamento.model;

import br.com.faturamento.model.enums.TipoLancamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "lancamento")
public class LancamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @NotNull
    private BigDecimal valor;

    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    private Date dataLancamento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
    private Date registro = new Date();

    public LancamentoModel() {

    }

    public LancamentoModel(BigDecimal valor, String descricao, TipoLancamento tipo, Date dataLancamento) {
        this.valor = valor;
        this.descricao = descricao;
        this.tipo = tipo;
        this.dataLancamento = dataLancamento;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public Date getRegistro() {
        return registro;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public void setRegistro(Date registro) {
        this.registro = registro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LancamentoModel that = (LancamentoModel) o;
        return Objects.equals(codigo, that.codigo) && Objects.equals(valor, that.valor) && Objects.equals(descricao, that.descricao) && tipo == that.tipo && Objects.equals(dataLancamento, that.dataLancamento) && Objects.equals(registro, that.registro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, valor, descricao, tipo, dataLancamento, registro);
    }
}
