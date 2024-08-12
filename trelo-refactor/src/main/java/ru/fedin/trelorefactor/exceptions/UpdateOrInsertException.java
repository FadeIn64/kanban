package ru.fedin.trelorefactor.exceptions;

public class UpdateOrInsertException extends RuntimeException{

    public UpdateOrInsertException() {
    }

    public UpdateOrInsertException(String message) {
        super(message);
    }

    public UpdateOrInsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateOrInsertException(Throwable cause) {
        super(cause);
    }

    public UpdateOrInsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
