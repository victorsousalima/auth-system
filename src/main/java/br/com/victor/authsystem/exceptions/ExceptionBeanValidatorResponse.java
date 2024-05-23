package br.com.victor.authsystem.exceptions;

import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ExceptionBeanValidatorResponse extends ExceptionResponse{

    private List<InvalidField> invalidFields = new ArrayList<>();

    public ExceptionBeanValidatorResponse(Instant moment, String message, String details) {
        super(moment, message, details);
    }

}
