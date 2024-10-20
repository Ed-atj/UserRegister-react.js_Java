package com.estudos.app.exception.user;

import com.estudos.app.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends BaseException {


    public UserAlreadyExistsException(String email){
        super(HttpStatus.CONFLICT, "User already exists"
        ,"User with email: "+ email +" already exists.");
    }

    public UserAlreadyExistsException(Long id){
        super(HttpStatus.NOT_FOUND, "User already exists"
        , "User with email");
    }
}
