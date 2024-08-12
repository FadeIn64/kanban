package ru.fedin.trelorefactor.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.fedin.trelorefactor.exceptions.EntityNotFound;
import ru.fedin.trelorefactor.exceptions.UpdateOrInsertException;

import static org.springframework.http.HttpStatus.*;
@ControllerAdvice
public class Advice {

    @ExceptionHandler(EntityNotFound.class)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public String notFound(EntityNotFound e){
        return e.getMessage();
    }

    @ExceptionHandler(UpdateOrInsertException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public String notFound(UpdateOrInsertException e){
        return e.getMessage();
    }


}
