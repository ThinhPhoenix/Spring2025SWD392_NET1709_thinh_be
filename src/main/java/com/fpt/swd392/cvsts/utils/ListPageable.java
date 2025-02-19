package com.fpt.swd392.cvsts.utils;

import lombok.Data;

@Data
public class ListPageable<T> {
    private T list;
    private Paging paging;
}
