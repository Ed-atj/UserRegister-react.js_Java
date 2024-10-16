package com.estudos.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class BaseException extends RuntimeException {

    public  ProblemDetail problemDetail() {
        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("Internal server error.");
        pb.setDetail("Something went wrong in base application");
        return pb;
    }

}
