package br.com.faturamento.dto;

import java.util.Objects;

public class InvoiceDTO {

    private String code;
    private String price;
    private String description;
    private String type;
    private String dateRelease;
    private String registration;

    public InvoiceDTO() {
    }

    public InvoiceDTO(String code, String price, String description, String type, String dateRelease, String registration) {
        this.code = code;
        this.price = price;
        this.description = description;
        this.type = type;
        this.dateRelease = dateRelease;
        this.registration = registration;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(String dateRelease) {
        this.dateRelease = dateRelease;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceDTO that = (InvoiceDTO) o;
        return Objects.equals(code, that.code) && Objects.equals(price, that.price) && Objects.equals(description, that.description) && Objects.equals(type, that.type) && Objects.equals(dateRelease, that.dateRelease) && Objects.equals(registration, that.registration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, price, description, type, dateRelease, registration);
    }
}
