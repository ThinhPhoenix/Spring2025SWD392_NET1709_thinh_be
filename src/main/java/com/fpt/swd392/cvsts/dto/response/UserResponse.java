package com.fpt.swd392.cvsts.dto.response;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import com.fpt.swd392.cvsts.entities.User;

import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String fullname;
    private String email;
    private LocalDateTime createdAt;

    public UserResponse(User user) {
        this.id = user.getId();
        this.fullname = user.getFullname();
        this.email = user.getEmail();
        this.createdAt = LocalDateTime.now();
    }
}
