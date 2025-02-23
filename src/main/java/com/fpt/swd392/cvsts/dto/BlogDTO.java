package com.fpt.swd392.cvsts.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BlogDTO {
    private String blogId;
    private String title;
    private String shortDescription;
    private String content;
    private String imageUrl;
    private String blogStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
    private String authorId;
    private String authorFullName;
    private String authorEmail;
    private String authorAvatarUrl;
    private String reviewerId;
    private String reviewerFullName;
    private String reviewerEmail;
    private String reviewerAvatarUrl;
}