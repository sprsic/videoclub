package com.sprsic.model.common;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
public class FailureJsonResponse extends JsonResponse {

    private Object errors;

    public FailureJsonResponse(final Object errors) {
        super(false);
        this.errors = errors;
    }

    public FailureJsonResponse(final ResponseMsg message, final Object errors) {
        super(false, message);
        this.errors = errors;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(final Object errors) {
        this.errors = errors;
    }
}
