package com.estudos.app.exception;

import com.estudos.app.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserAlreadyExistsException extends BaseException {

    private UserDto userDto;

    @Override
    public ProblemDetail problemDetail(){
        ProblemDetail pb  = ProblemDetail.forStatus(HttpStatus.CONFLICT);

        pb.setTitle("User already exists:");
        pb.setDetail("User with email: "+ userDto.email() +".\nAlready exists.");
        return pb;
    }
}
