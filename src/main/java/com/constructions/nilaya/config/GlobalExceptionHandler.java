package com.constructions.nilaya.config;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, DuplicateKeyException.class})
    public ResponseEntity<Map<String, Object>> handleExceptions(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");

        if (ex instanceof MethodArgumentNotValidException) {
            response.put("message", "Validation failed");

            Map<String, String> errors = new HashMap<>();
            ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors().forEach((FieldError error) ->
                errors.put(error.getField(), error.getDefaultMessage()));
            
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        } else if (ex instanceof DuplicateKeyException) {
            response.put("message", "A record with the same key already exists.");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        // Default fallback in case the exception type isn't specifically handled
        response.put("message", "An unexpected error occurred.");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
