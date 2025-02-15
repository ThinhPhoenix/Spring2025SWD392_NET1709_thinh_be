package com.fpt.swd392.cvsts.utils;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse {
    private String code;           
    private String message;        
    private LocalDateTime timestamp;  
    private List<String> details; 
    
    public ErrorResponse(String code, String message, List<String> details) {
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }
    
}
