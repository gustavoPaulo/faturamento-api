package br.com.billing.faturamento.model.enums;

public enum TypeInvoice {

    RECIPE("Recipe"),
    EXPENSE("Expense");

    private final String description;

    TypeInvoice(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
