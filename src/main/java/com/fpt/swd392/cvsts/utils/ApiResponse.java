package com.fpt.swd392.cvsts.utils;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private String code;
    private T data;
    private String message;
    private Paging paging;

    public ApiResponse(String code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.paging = null;
    }
}
