package com.scc4.purejs.error;

public class NotFound extends Exception {
    private String message;

    public NotFound(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
