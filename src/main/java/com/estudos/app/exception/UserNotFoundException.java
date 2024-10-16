package com.estudos.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserNotFoundException extends BaseException {

    @Override
    public ProblemDetail problemDetail(){
        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pb.setTitle("User not exist.");
        pb.setDetail("Unable to update a non-existing user.");
        return pb;
    }
}
