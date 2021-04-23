package com.github.arturkolaska.reposapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorContainer extends RepositoryModel {

    @JsonProperty("error-code")
    private final int errorCode;
    @JsonProperty("error-message")
    private final String errorMessage;

    public ErrorContainer(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
