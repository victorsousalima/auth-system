package br.com.victor.authsystem.dto;

import java.io.Serializable;

import br.com.victor.authsystem.entities.User;

public class UserResponse implements Serializable{
    
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String typeUser;

    public UserResponse() {}

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.typeUser = user.getTypeUser().getLabel();
    }

    public UserResponse(Long id, String name, String email, String phone, String typeUser) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.typeUser = typeUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    
}
