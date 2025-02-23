package com.fpt.swd392.cvsts.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListPageable<T> {
    private T list;
    private Paging paging;
}
