package com.sprsic.model.common;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
public class FieldErrorModel {

    private String field;
    private String message;

    public FieldErrorModel(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
