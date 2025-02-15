package com.fpt.swd392.cvsts.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Paging {
    private int currentPage;
    private int pageSize;
    private int totalPages;

    public Paging(int currentPage, int pageSize, int totalPages) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
    }
}
