package ru.fedin.trelorefactor.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModifyDataException extends RuntimeException{

    public ModifyDataException() {
    }

    public ModifyDataException(String message) {
        super(message);
    }

    public ModifyDataException(String message, Throwable cause) {
        super(message, cause);
        log.error("Error: ", cause);
    }

    public ModifyDataException(Throwable cause) {
        super(cause);
        log.error("Error: ", cause);
    }

    public ModifyDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        log.error("Error: ", cause);
    }
}
