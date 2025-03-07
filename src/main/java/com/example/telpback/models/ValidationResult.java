package com.example.telpback.models;

import org.springframework.http.HttpStatus;

public class ValidationResult {
    private boolean errorStatus;
    private String message;
    private HttpStatus httpStatus;

    public ValidationResult(boolean errorStatus, HttpStatus status, String message) {
        this.errorStatus = errorStatus;
        this.message = message;
        this.httpStatus = status;
    }

    public ValidationResult() {}

    public boolean getErrorStatus() {
        return errorStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
