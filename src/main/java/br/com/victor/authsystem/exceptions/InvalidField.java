package br.com.victor.authsystem.exceptions;

public record InvalidField(
        String field,
        String message
) {
}
