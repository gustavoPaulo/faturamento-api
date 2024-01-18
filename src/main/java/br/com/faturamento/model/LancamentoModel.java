package br.com.faturamento.model;

import br.com.faturamento.model.enums.TipoLancamento;
import br.com.faturamento.useful.Utils;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = Utils.ENTITY_NAME_LANCAMENTO)
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Utils.DATE_PATTERN, locale = Utils.DATE_LOCALE, timezone = Utils.DATE_TIMEZONE)
    private LocalDate dataLancamento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Utils.DATE_TIME_PATTERN, locale = Utils.DATE_LOCALE, timezone = Utils.DATE_TIMEZONE)
    private LocalDateTime registro = LocalDateTime.now();

    public LancamentoModel() {

    }

    public LancamentoModel(BigDecimal valor, String descricao, TipoLancamento tipo, LocalDate dataLancamento) {
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

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public LocalDateTime getRegistro() {
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

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public void setRegistro(LocalDateTime registro) {
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
