package com.github.arturkolaska.reposapi.controller;

import com.github.arturkolaska.reposapi.exception.WebClientErrorException;
import com.github.arturkolaska.reposapi.model.ErrorContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.concurrent.TimeoutException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.REQUEST_TIMEOUT;

@ControllerAdvice
public class ControllerExceptionHelper {

    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHelper.class);


    @ExceptionHandler(value = WebClientErrorException.class)
    public ResponseEntity<Object> handleWebClientErrorException(WebClientErrorException ex) {
        log.error(ex.toString());
        return new ResponseEntity<>(new ErrorContainer(ex.getCode(), ex.getStatus()), ex.getStatus());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNotFoundException() {
        log.error(NOT_FOUND.toString());
        return new ResponseEntity<>(new ErrorContainer(404, NOT_FOUND), NOT_FOUND);
    }

    @ExceptionHandler(value = TimeoutException.class)
    public ResponseEntity<Object> handleTimeoutException(TimeoutException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(new ErrorContainer(408, REQUEST_TIMEOUT), REQUEST_TIMEOUT);
    }
}
