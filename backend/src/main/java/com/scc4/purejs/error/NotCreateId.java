package com.scc4.purejs.error;

public class NotCreateId extends Exception {
    private String message;

    public NotCreateId() {
        this.message = "Não é possível criar um cliente com id";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
