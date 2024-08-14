package ru.fedin.trelorefactor.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateOrInsertException extends RuntimeException{

    public UpdateOrInsertException() {
    }

    public UpdateOrInsertException(String message) {
        super(message);
    }

    public UpdateOrInsertException(String message, Throwable cause) {
        super(message, cause);
        log.error("Error: ", cause);
    }

    public UpdateOrInsertException(Throwable cause) {
        super(cause);
        log.error("Error: ", cause);
    }

    public UpdateOrInsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        log.error("Error: ", cause);
    }
}
