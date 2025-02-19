package com.fpt.swd392.cvsts.services;

import com.fpt.swd392.cvsts.entities.Blog;
import com.fpt.swd392.cvsts.repositories.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogService implements IBlogService {

    private final BlogRepository blogRepository;

    @Override
    @Transactional
    public Blog createBlog(Blog blog) {
        blog.setId(UUID.randomUUID().toString());
        blog.setCreatedAt(LocalDateTime.now());
        blog.setStatus("DRAFT"); // Default status
        return blogRepository.save(blog);
    }

    @Override
    @Transactional
    public Blog updateBlog(Blog blog) {
        Blog existingBlog = getBlogById(blog.getId());
        
        existingBlog.setTitle(blog.getTitle());
        existingBlog.setContent(blog.getContent());
        existingBlog.setStatus(blog.getStatus());
        existingBlog.setReviewerId(blog.getReviewerId());
        existingBlog.setUpdatedAt(LocalDateTime.now());
        
        if ("PUBLISHED".equals(blog.getStatus())) {
            existingBlog.setPublishedAt(LocalDateTime.now());
        }
        
        return blogRepository.save(existingBlog);
    }

    @Override
    @Transactional
    public void deleteBlog(String blogId) {
        if (!blogRepository.existsById(blogId)) {
            throw new RuntimeException("Blog not found with ID: " + blogId);
        }
        blogRepository.deleteById(blogId);
    }

    @Override
    @Transactional(readOnly = true)
    public Blog getBlogById(String blogId) {
        return blogRepository.findById(blogId)
            .orElseThrow(() -> new RuntimeException("Blog not found with ID: " + blogId));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Blog> getAllBlogs(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }
}