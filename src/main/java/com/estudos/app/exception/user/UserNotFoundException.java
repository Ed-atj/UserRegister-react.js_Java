package com.estudos.app.exception.user;

import com.estudos.app.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {


    public UserNotFoundException(Long id){
        super(HttpStatus.NOT_FOUND, "User not found."
        , "Unable to find user id: "+ id + ".");
    }

    public UserNotFoundException(String email){
        super(HttpStatus.NO_CONTENT, "User not found"
                ,"Unable to find user id: "+ email+ ".");
    }


    public UserNotFoundException(int size){
        super(HttpStatus.NOT_FOUND, "List of users exception."
                ,"Unable to list users.\nSize: " + size + ".");
    }
}
