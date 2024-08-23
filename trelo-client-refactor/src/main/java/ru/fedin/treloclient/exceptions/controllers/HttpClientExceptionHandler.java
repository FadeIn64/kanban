package ru.fedin.treloclient.exceptions.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientResponseException;

@ControllerAdvice
@Slf4j
public class HttpClientExceptionHandler {

    @ExceptionHandler(RestClientResponseException.class)
    ResponseEntity<String> handleRestClientResponseException(RestClientResponseException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ex.getResponseBodyAsString(), ex.getStatusCode());
    }

}
