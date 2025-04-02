package br.com.billing.faturamento.model.enums;

public enum InvoiceType {

    RECIPE("Recipe"),
    EXPENSE("Expense");

    private final String description;

    InvoiceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
