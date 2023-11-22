package br.com.victor.authsystem.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;

public class UserUpdatePasswordRequest implements Serializable{

    @NotEmpty
    private String actualPassword;
    
    @NotEmpty
    private String newPassword;

    @NotEmpty
    private String newPasswordConfirmation;
    
    public UserUpdatePasswordRequest() {}

    public UserUpdatePasswordRequest(@NotEmpty String actualPassword, @NotEmpty String newPassword,
            @NotEmpty String newPasswordConfirmation) {
        this.actualPassword = actualPassword;
        this.newPassword = newPassword;
        this.newPasswordConfirmation = newPasswordConfirmation;
    }

    public String getActualPassword() {
        return actualPassword;
    }

    public void setActualPassword(String actualPassword) {
        this.actualPassword = actualPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirmation() {
        return newPasswordConfirmation;
    }

    public void setNewPasswordConfirmation(String newPasswordConfirmation) {
        this.newPasswordConfirmation = newPasswordConfirmation;
    }

}
