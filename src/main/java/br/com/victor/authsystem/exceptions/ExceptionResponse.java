package br.com.victor.authsystem.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class ExceptionResponse implements Serializable{

    private Instant moment;
    private String message;
    private String details;
    
    public ExceptionResponse(Instant moment, String message, String details) {
        this.moment = moment;
        this.message = message;
        this.details = details;
    }

    public Instant getMoment() {
        return moment;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
    
}