package br.com.billing.faturamento.model;

public class MailModel {

    private String status;
    private String message;
    private String destination;
    private InvoiceModel invoice;

    public MailModel() {
    }

    public MailModel(String status, String message, String destination, InvoiceModel invoice) {
        this.status = status;
        this.message = message;
        this.destination = destination;
        this.invoice = invoice;
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

    public InvoiceModel getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceModel invoice) {
        this.invoice = invoice;
    }
}
