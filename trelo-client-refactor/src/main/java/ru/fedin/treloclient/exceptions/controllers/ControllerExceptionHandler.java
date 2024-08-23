package ru.fedin.treloclient.exceptions.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.fedin.treloclient.exceptions.EntityNotFoundException;
import ru.fedin.treloclient.exceptions.ModifyDataException;


import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public String entityNotFound(EntityNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(ModifyDataException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public String modifyData(ModifyDataException e){
        return e.getMessage();
    }


}
