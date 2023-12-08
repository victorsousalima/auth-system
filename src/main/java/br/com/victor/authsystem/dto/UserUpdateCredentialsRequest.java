package br.com.victor.authsystem.dto;

import br.com.victor.authsystem.entities.User;
import jakarta.validation.constraints.Pattern;

public record UserUpdateCredentialsRequest(
        String name,
        String email,
        @Pattern(regexp = "\\d{14}")
        String phone
) {

    public User convertToUser(Long id) {
        return new User(id, name(), email(), phone(), null);
    }
}
