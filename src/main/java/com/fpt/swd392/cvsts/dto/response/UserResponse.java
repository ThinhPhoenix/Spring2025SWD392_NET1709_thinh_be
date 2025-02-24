package com.fpt.swd392.cvsts.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fpt.swd392.cvsts.dto.AddressDTO;
import com.fpt.swd392.cvsts.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private String token;
    private String id;
    private String fullname;
    private String email;
    private String phoneNumber;
    private String gender;
    private AddressDTO address;
    private String role;
    private LocalDate birthday;
    private LocalDateTime createdAt;

    public UserResponse(User user) {
        this.id = user.getId();
        this.fullname = user.getFullname();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.role = user.getRole();
        this.birthday = user.getBirthday();
        this.createdAt = LocalDateTime.now();
    }
}
