package br.com.billing.faturamento.model;

import java.util.List;

public class MailModel {

    private String status;
    private String message;
    private String destination;
    private List<InvoiceModel> invoices;

    public MailModel() {
    }

    public MailModel(String status, String message, String destination, List<InvoiceModel> invoices) {
        this.status = status;
        this.message = message;
        this.destination = destination;
        this.invoices = invoices;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<InvoiceModel> getInvoice() {
        return invoices;
    }

    public void setInvoice(List<InvoiceModel> invoices) {
        this.invoices = invoices;
    }
}
