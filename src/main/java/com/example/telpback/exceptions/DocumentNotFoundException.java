package com.example.telpback.exceptions;

public class DocumentNotFoundException extends RuntimeException{
    public DocumentNotFoundException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
