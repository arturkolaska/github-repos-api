package com.github.arturkolaska.reposapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorContainer {

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

    @Override
    public int hashCode() {
        return Objects.hash(errorCode, errorMessage);
    }

    @Override
    public String toString() {
        return "ErrorContainer{errorCode=" + errorCode + ", errorMessage=" + errorMessage + '}';
    }
}
