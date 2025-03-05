package com.example.telpback.models;

import org.springframework.http.HttpStatus;

public class ValidationResult {
    private boolean error;
    private String message;
    private HttpStatus status;

    public ValidationResult(boolean error, HttpStatus status, String message) {
        this.error = error;
        this.message = message;
        this.status = status;
    }

    public ValidationResult() {}

    public boolean isValid() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
