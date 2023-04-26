package com.scc4.purejs.response;

public class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(Exception exception) {
        this.message = exception.getMessage();
    }

    public String getMessage() {
        return message;
    }
}
