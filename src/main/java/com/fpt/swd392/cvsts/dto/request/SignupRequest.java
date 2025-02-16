package com.fpt.swd392.cvsts.dto.request;

import java.time.LocalDate;

import com.fpt.swd392.cvsts.dto.AddressDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank(message = "Fullname is required")
    // @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Fullname must be alphanumeric")
    private String fullname;

    @Valid
    @NotNull(message = "Address is required")
    private AddressDTO address;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Invalid phone number format")
    private String phoneNumber;

    @NotNull(message = "Birthday is required")
    @Past(message = "Birthday must be in the past")
    private LocalDate birthday;

    @NotNull(message = "Gender is required")
    @Pattern(regexp = "^(M|F|)$", message = "Gender must be M, F")
    private String gender;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$", 
            message = "Invalid password")
    private String password;

    @NotNull(message = "Role is required")
    private String role;
}
