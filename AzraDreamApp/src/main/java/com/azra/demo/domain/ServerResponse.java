package com.azra.demo.domain;

public class ServerResponse<T> {

    private T result;
    private String message;
    private boolean success;

    private static final String SUCCESS_MESSAGE = "200, Successful";
    private static final String FAILURE_MESSAGE = "Not Successful";

    public ServerResponse(boolean success){
        this.success = success;
        if(this.success) {

            this.message = SUCCESS_MESSAGE;
        } else {
            this.message = FAILURE_MESSAGE;
        }

    }
    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
