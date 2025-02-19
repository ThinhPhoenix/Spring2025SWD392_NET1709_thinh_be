package com.fpt.swd392.cvsts.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blogs")
public class Blog {
    
    @Id
    private String id;

    @Column(name = "author_id")
    private String authorId;

    @Column(name = "reviewer_id")
    private String reviewerId;

    private String title;

    @Column(columnDefinition = "text")
    private String content;

    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;
}