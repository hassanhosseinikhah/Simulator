package com.example.simualtor.exception;

public class RException extends Exception {
    private String message;
    private int status;

    public RException(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public RException(String message, String message1, int status) {
        super(message);
        this.message = message1;
        this.status = status;
    }

    public RException(String message, Throwable cause, String message1, int status) {
        super(message, cause);
        this.message = message1;
        this.status = status;
    }

    public RException(Throwable cause, String message, int status) {
        super(cause);
        this.message = message;
        this.status = status;
    }

    public RException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1, int status) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
        this.status = status;
    }
}
