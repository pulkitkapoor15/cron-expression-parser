package com.deliveroo.exceptions;

import com.deliveroo.model.TimeToken;

public class InvalidCronException extends RuntimeException {
    
    public InvalidCronException(String exceptionMessage) {
        super(exceptionMessage);
    } 

    public InvalidCronException(TimeToken timeToken, String cron, String message) {
        StringBuilder exception = new StringBuilder();
        exception.append("The expression " + cron + " is invalid for " + timeToken.getName());
        exception.append("\n The range for " + timeToken.getName() + " is : " + timeToken.getLowerLimit() + " - " + timeToken.getUpperLimit());
        throw new InvalidCronException(exception.toString());
    }

}
