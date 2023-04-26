package com.scc4.purejs.error;

public class MissingProperty extends Exception {
    private String message;

    public MissingProperty(String property) {
        this.message = "A propriedade \"" + property + "\" é obrigatória";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
