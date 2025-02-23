package com.fpt.swd392.cvsts.utils;

import java.util.UUID;

public class Utils {
    public static PageBound calculatePageBounds (int currentPage, int pageSize, int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Total length cannot be negative");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be positive");
        }
        if (currentPage <= 0) {
            throw new IllegalArgumentException("Current page must be positive");
        }

        PageBound pageBound = new PageBound();
        
        // Calculate start index
        int startIndex = (currentPage - 1) * pageSize;
        
        // Check if start index is beyond the list length
        if (startIndex >= length) {
            throw new IllegalArgumentException(
                String.format("Page %d is beyond the last page. Total items: %d, Page size: %d", 
                    currentPage, length, pageSize)
            );
        }
        
        pageBound.setStartIndex(startIndex);
        
        // Calculate end index
        // Math.min ensures we don't exceed the list length
        pageBound.setEndIndex(Math.min(currentPage * pageSize, length));
        
        return pageBound;
    }

    public static int calculateTotalPage(int totalItems, int pageSize) {
        return (int) Math.ceil((double) totalItems / pageSize);
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
