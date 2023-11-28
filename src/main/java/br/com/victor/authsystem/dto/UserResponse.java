package br.com.victor.authsystem.dto;

import br.com.victor.authsystem.entities.User;
import br.com.victor.authsystem.entities.enums.TypeUser;

public record UserResponse(
    String name, 
    String email, 
    String phone, 
    TypeUser typeUser) {
    

        public UserResponse(User user) {
            this(user.getName(), user.getEmail(), user.getPhone(), user.getTypeUser());
        }
}
