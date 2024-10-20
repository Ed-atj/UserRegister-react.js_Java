package com.estudos.app.logging;

import org.springframework.stereotype.Component;

@Component
public class ServiceLogging extends BaseLogging{

    protected ServiceLogging() {
        super(ServiceLogging.class);
    }
}
