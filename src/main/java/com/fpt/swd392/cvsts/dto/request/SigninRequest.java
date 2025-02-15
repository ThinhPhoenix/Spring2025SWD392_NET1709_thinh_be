package com.fpt.swd392.cvsts.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SigninRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
