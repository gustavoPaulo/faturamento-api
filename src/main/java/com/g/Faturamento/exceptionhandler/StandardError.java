package com.g.Faturamento.exceptionhandler;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class StandardError implements Serializable {

    private String message;
    private int code;
    private String status;
    private String objectName;
    private List<ErrorObject> errors;
    private String dateTime;

    public StandardError(int code, String message, String dateTime) {
        this.code = code;
        this.message = message;
        this.dateTime = dateTime;
    }

    public StandardError(String message, int code, String status, String objectName, List<ErrorObject> errors, String dateTime) {
        this.message = message;
        this.code = code;
        this.status = status;
        this.objectName = objectName;
        this.errors = errors;
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public List<ErrorObject> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorObject> errors) {
        this.errors = errors;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}