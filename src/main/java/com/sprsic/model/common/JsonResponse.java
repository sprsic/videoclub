package com.sprsic.model.common;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
public class JsonResponse {

    private boolean success;
    private ResponseMsg message;

    public JsonResponse(final boolean success) {
        this.success = success;
    }

    public JsonResponse(final boolean success, final ResponseMsg message) {
        this(success);
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ResponseMsg getMessage() {
        return message;
    }

    public void setMessage(ResponseMsg message) {
        this.message = message;
    }
}
