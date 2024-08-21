package ru.fedin.trelorefactor.exceptions.controllers;

import org.springframework.web.bind.annotation.*;
import ru.fedin.trelorefactor.exceptions.EntityNotFoundException;
import ru.fedin.trelorefactor.exceptions.ModifyDataException;

import static org.springframework.http.HttpStatus.*;
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
