package com.ghh.canvas;

public class IllegalCommandException extends Exception {
    public IllegalCommandException() {
    }

    public IllegalCommandException(String message) {
        super(message);
    }

    public IllegalCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalCommandException(Throwable cause) {
        super(cause);
    }

    public IllegalCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
