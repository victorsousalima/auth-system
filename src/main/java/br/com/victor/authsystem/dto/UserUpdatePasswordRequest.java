package br.com.victor.authsystem.dto;

import jakarta.validation.constraints.NotBlank;

public record UserUpdatePasswordRequest(
    @NotBlank
    String actualPassword,

    @NotBlank
    String newPassword,

    @NotBlank
    String newPasswordConfirmation) {
    
}
