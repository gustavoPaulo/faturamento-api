package br.com.billing.faturamento.model;

import br.com.billing.faturamento.model.enums.InvoiceType;

import java.util.Objects;

public class InvoiceFilterModel {

    private String description;
    private double priceMin;
    private double priceMax;
    private String type;
    private String dateStart;
    private String dateEnd;

    public InvoiceFilterModel() {
    }

    public InvoiceFilterModel(String description, double priceMin, double priceMax, String type, String dateStart, String dateEnd) {
        this.description = description;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.type = type;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(double priceMin) {
        this.priceMin = priceMin;
    }

    public double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(double priceMax) {
        this.priceMax = priceMax;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        InvoiceFilterModel that = (InvoiceFilterModel) object;
        return Double.compare(priceMin, that.priceMin) == 0 && Double.compare(priceMax, that.priceMax) == 0 && Objects.equals(description, that.description) && type == that.type && Objects.equals(dateStart, that.dateStart) && Objects.equals(dateEnd, that.dateEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, priceMin, priceMax, type, dateStart, dateEnd);
    }
}
