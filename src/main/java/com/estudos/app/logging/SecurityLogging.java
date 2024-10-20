package com.estudos.app.logging;

import org.springframework.stereotype.Component;

@Component
public class SecurityLogging extends BaseLogging{

    public SecurityLogging(){
        super(SecurityLogging.class);
    }
}
