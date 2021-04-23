package com.github.arturkolaska.reposapi.exception;

public class WebClientErrorException extends IllegalStateException {

    private final int code;

    public WebClientErrorException(int code) {
        this.code = code;
    }

    public WebClientErrorException(String message, int code) {
        super("Encountered a problem calling api.github.com" + message);
        this.code = code;
    }

    public WebClientErrorException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public WebClientErrorException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}