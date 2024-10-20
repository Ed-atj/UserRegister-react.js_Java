package com.estudos.app.exception.user;

import com.estudos.app.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UserAuthenticationException extends BaseException {

    public UserAuthenticationException(){
        super(HttpStatus.UNAUTHORIZED, "User authentication failed"
        ,"Unable to receive user authentication email or token.");
    }
}
