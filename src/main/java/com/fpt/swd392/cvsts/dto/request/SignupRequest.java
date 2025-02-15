package com.fpt.swd392.cvsts.dto.request;

import java.time.LocalDate;

import com.fpt.swd392.cvsts.dto.AddressDTO;

import lombok.Data;

@Data
public class SignupRequest {
    private String fullname;
    private AddressDTO address;
    private String phoneNumber;
    private LocalDate birthday;
    private String gender;
    private String email;
    private String password;
    private String role;
}
