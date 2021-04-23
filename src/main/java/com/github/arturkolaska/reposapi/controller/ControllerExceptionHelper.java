package com.github.arturkolaska.reposapi.controller;

import com.github.arturkolaska.reposapi.exception.WebClientErrorException;
import com.github.arturkolaska.reposapi.model.ErrorContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.TimeoutException;

import static com.github.arturkolaska.reposapi.constants.StringConstants.WEBCLIENT_TIMEOUT;

@ControllerAdvice
public class ControllerExceptionHelper {

    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHelper.class);

    @ExceptionHandler(value = {WebClientErrorException.class})
    public ResponseEntity<Object> handleWebClientErrorException(WebClientErrorException ex) {
        log.error(ex.getCode() + ": " + ex.getMessage());
        return new ResponseEntity<>(new ErrorContainer(ex.getCode(), ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {TimeoutException.class})
    public ResponseEntity<Object> handleTimeoutException(TimeoutException ex) {
        log.error(408 + ": " + ex.getMessage());
        return new ResponseEntity<>(new ErrorContainer(408, WEBCLIENT_TIMEOUT),
                HttpStatus.REQUEST_TIMEOUT);
    }
}
