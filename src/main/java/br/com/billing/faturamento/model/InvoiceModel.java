package br.com.billing.faturamento.model;

import br.com.billing.faturamento.model.enums.TypeInvoice;
import br.com.billing.faturamento.useful.Utility;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = Utility.ENTITY_NAME_INVOICE)
public class InvoiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer code;

    @NotNull
    private BigDecimal price;

    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeInvoice type;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Utility.DATE_PATTERN, locale = Utility.DATE_LOCALE, timezone = Utility.DATE_TIMEZONE)
    private LocalDate dateRelease;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Utility.DATE_TIME_PATTERN, locale = Utility.DATE_LOCALE, timezone = Utility.DATE_TIMEZONE)
    private LocalDateTime registration = LocalDateTime.now();

    public InvoiceModel() {
    }

    public InvoiceModel(BigDecimal price, String description, TypeInvoice type, LocalDate dateRelease) {
        this.price = price;
        this.description = description;
        this.type = type;
        this.dateRelease = dateRelease;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeInvoice getType() {
        return type;
    }

    public void setType(TypeInvoice type) {
        this.type = type;
    }

    public LocalDate getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(LocalDate dateRelease) {
        this.dateRelease = dateRelease;
    }

    public LocalDateTime getRegistration() {
        return registration;
    }

    public void setRegistration(LocalDateTime registration) {
        this.registration = registration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceModel that = (InvoiceModel) o;
        return Objects.equals(code, that.code) && Objects.equals(price, that.price) && Objects.equals(description, that.description) && type == that.type && Objects.equals(dateRelease, that.dateRelease) && Objects.equals(registration, that.registration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, price, description, type, dateRelease, registration);
    }
}
