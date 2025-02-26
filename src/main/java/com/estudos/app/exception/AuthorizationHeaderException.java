package com.estudos.app.exception;

import org.springframework.http.HttpStatus;

public class AuthorizationHeaderException extends BaseException {

    public AuthorizationHeaderException() {
        super(HttpStatus.UNAUTHORIZED
        ,"Authorization Header Error."
        ,"Authorization Header is null or empty.");
    }
}
