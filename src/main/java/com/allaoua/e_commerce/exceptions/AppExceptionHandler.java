package com.allaoua.e_commerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> productNotFound(ProductNotFoundException e) {
        ErrorMessage errorMessage= ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorMessage> categoryNotFound(CategoryNotFoundException e) {
        ErrorMessage errorMessage= ErrorMessage.builder()
                .message(e.getMessage())
                .code(404)
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
