package br.com.faturamento.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

public class StandardError {

    private int status;
    private String message;
    private String dateTime;
    private List<FieldMessage> errors = new ArrayList<>();

    public StandardError(int status, String message, String dateTime) {
        this.status = status;
        this.message = message;
        this.dateTime = dateTime;
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldMessage> errors) {
        this.errors = errors;
    }
}
