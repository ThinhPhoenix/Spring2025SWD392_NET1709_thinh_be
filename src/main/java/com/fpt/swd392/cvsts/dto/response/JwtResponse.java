package com.fpt.swd392.cvsts.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtResponse {
    private String token;
    private String id;
    private String email;
    private String role;

    public JwtResponse(String token, String id, String email, String role) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public JwtResponse(String token) {
        this.token = token;
    }

    
}
