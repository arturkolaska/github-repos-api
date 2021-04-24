package com.github.arturkolaska.reposapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public class ErrorContainer extends RepositoryModel {

    @JsonProperty("error-code")
    private final int errorCode;
    @JsonProperty("error-message")
    private final HttpStatus errorMessage;

    public ErrorContainer(int errorCode, HttpStatus errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public HttpStatus getErrorMessage() {
        return errorMessage;
    }
}
