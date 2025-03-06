package com.example.telpback.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, Object> response = new HashMap<>();
        List<String> missingFields = new ArrayList<>();

        response.put("message", "Object validation failed on request.");

        //not proud of it but it works. i'm having some trouble figuring out how to differentiate validation errors
        //because my custom validator throws the same error as other annotations like @NotBlank. whatever

        if (exception.getMessage().contains("Both likedPlaces and visitedPlaces cannot exist.")) {
            response.put("error", "Both likedPlaces and visitedPlaces cannot exist.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (exception.getMessage().contains("Both likedPlaces and visitedPlaces cannot be null.")) {
            response.put("error", "Both likedPlaces and visitedPlaces cannot be null.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            missingFields.add(field);
        });

        response.put("missingFields", missingFields);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        Map<String, Object> response = new HashMap<>();

        response.put("message", "Issue with request. Check additionalInfo for a detailed error.");
        response.put("additionalInfo", exception.getMessage());
        System.out.println(exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolationException(ConstraintViolationException exception) {
        Map<String, Object> response = new HashMap<>();

        response.put("message", "attempting to handle constraint violation");

        System.out.println(exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
