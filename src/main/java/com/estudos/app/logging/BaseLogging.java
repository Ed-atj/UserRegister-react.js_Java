package com.estudos.app.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseLogging {

    protected  final Logger logger;

    protected BaseLogging(Class<?> setClass){
        this.logger = LoggerFactory.getLogger(setClass);
    }
    public void info(String message){
        logger.info(message);
    }
    public void warn(String message){
        logger.warn(message);
    }
    public void error(String message){
        logger.error(message);
    }
    public void debug(String message){
        logger.debug(message);
    }

    public void infoOperation( String status, String operation, String identifier){
        switch(status){
            case "start" -> info("Starting: "+ operation + " for " + identifier + ".");
            case "success" -> info("Success: "+ operation + " for " + identifier + ".");
        }
    }

    public void warnFindOperations(String select, String method, String target){
        switch(select){
            case "missing" -> warn(method +": " + target + " not found or is empty.");
            case "exists" -> warn(method +": " + target + " already exists.");
        }
    }
}
