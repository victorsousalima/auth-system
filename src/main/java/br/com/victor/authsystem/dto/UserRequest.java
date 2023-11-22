package br.com.victor.authsystem.dto;

import br.com.victor.authsystem.entities.User;
import br.com.victor.authsystem.entities.enums.TypeUser;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserRequest {
    
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    @NotEmpty
    @Size(min = 14, max = 14)
    private String phone;
    @NotEmpty
    private TypeUser typeUser;
    @NotEmpty
    private String password;

    public UserRequest() {}

    public UserRequest(@NotEmpty String name, @NotEmpty String email,
            @NotEmpty @Size(min = 14, max = 14) String phone, @NotEmpty TypeUser typeUser, @NotEmpty String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.typeUser = typeUser;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User convertToUser() {
        User user = new User(null,
            this.name,
            this.email,
            this.phone,
            this.password);
        
        user.setTypeUser(this.typeUser);

        return user;
    }
}
