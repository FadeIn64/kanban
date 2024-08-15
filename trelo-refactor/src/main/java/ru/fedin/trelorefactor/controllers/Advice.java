package ru.fedin.trelorefactor.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.fedin.trelorefactor.exceptions.EntityNotFound;
import ru.fedin.trelorefactor.exceptions.ModifyDataException;

import static org.springframework.http.HttpStatus.*;
@ControllerAdvice
public class Advice {

    @ExceptionHandler(EntityNotFound.class)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public String notFound(EntityNotFound e){
        return e.getMessage();
    }

    @ExceptionHandler(ModifyDataException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public String notFound(ModifyDataException e){
        return e.getMessage();
    }


}
