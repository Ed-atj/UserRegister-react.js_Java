package com.estudos.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UsersListNotFoundException extends BaseException {

    @Override
    public ProblemDetail problemDetail(){
        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pb.setTitle("List of users not found.");
        return pb;
    }
}
