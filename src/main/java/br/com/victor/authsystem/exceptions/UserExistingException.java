package br.com.victor.authsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserExistingException extends RuntimeException{
    
    public UserExistingException(String msg) {
        super(msg);
    }

}
