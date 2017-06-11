package com.sprsic.model.common;

/**
 * Written with love
 *
 * @author Sasa Prsic 11/06/2017
 */
public class SuccessJsonResponse extends JsonResponse {
    private Object data;

    public SuccessJsonResponse(final Object data) {
        super(true);
        this.data = data;
    }

    public SuccessJsonResponse(final ResponseMsg message, final Object data) {
        super(true, message);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
