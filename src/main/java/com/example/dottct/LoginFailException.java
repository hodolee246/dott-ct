package com.example.dottct;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class LoginFailException extends RuntimeException {

    private final String errorMessage;
    private final HttpStatus httpStatus;

}
