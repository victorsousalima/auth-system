package br.com.victor.authsystem.dto;

import br.com.victor.authsystem.entities.User;
import br.com.victor.authsystem.entities.enums.TypeUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserCreateRequest(
    @NotBlank
    String name,

    @NotBlank
    @Email
    String email,

    @NotBlank
    @Pattern(regexp = "\\d{14}")
    String phone,

    @NotNull
    TypeUser typeUser,

    @NotBlank
    String password) {


        public User convertToUser() {
            User user = new User(null, name(), email(), phone(), typeUser(), password());
            
            return user;
        }
    
}
