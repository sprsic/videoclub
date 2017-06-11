package com.sprsic.model.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
public class FormErrorModel {

    private List<FieldErrorModel> fieldErrors = new ArrayList<>();
    private String globalError;

    public List<FieldErrorModel> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldErrorModel> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public String getGlobalError() {
        return globalError;
    }

    public void setGlobalError(String globalError) {
        this.globalError = globalError;
    }
}
