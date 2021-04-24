package com.github.arturkolaska.reposapi.exception;

import org.springframework.http.HttpStatus;

public class WebClientErrorException extends IllegalStateException {

    private final int code;
    private final HttpStatus status;

    public WebClientErrorException(int code) {
        this.code = code;
        this.status = HttpStatus.valueOf(code);
    }

    public WebClientErrorException(String message, int code) {
        super(message);
        this.code = code;
        this.status = HttpStatus.valueOf(code);
    }

    public WebClientErrorException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
        this.status = HttpStatus.valueOf(code);
    }

    public WebClientErrorException(Throwable cause, int code) {
        super(cause);
        this.code = code;
        this.status = HttpStatus.valueOf(code);
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "WebClientErrorException: code=" + code + ", status=" + status;
    }
}