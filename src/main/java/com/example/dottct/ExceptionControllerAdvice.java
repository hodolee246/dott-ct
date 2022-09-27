package com.example.dottct;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(LoginFailException.class)
    public ResponseEntity<LoginFailException> exceptionHandler(LoginFailException ex) {
        return new ResponseEntity<>(ex, ex.getHttpStatus());
    }

}
