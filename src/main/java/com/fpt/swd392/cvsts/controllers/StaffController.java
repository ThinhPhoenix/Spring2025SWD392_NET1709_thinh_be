package com.fpt.swd392.cvsts.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fpt.swd392.cvsts.entities.Blog;
import com.fpt.swd392.cvsts.services.IBlogService;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private IBlogService blogService;

    public StaffController(IBlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/blog")
    public ResponseEntity<?> createBlog(@RequestBody Blog blog) {
        try {
            Blog createdBlog = blogService.createBlog(blog);
            return ResponseEntity.ok(createdBlog);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating blog: " + e.getMessage());
        }
    }

    @GetMapping("/blog")
    public ResponseEntity<?> getBlogById(@RequestParam String blogId) {
        return ResponseEntity.ok(blogService.getBlogById(blogId));
    }

    @PatchMapping("/blog")
    public ResponseEntity<?> updateBlog(@RequestBody Blog blog) {
        try {
            if (blog.getId() == null || blog.getId().isEmpty()) {
                return ResponseEntity.badRequest().body("Blog ID is required for update");
            }
            
            Blog updatedBlog = blogService.updateBlog(blog);
            return ResponseEntity.ok(updatedBlog);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating blog: " + e.getMessage());
        }
    }

    @DeleteMapping("/blog")
    public ResponseEntity<?> deleteBlog(@RequestParam String blogId) {
        try {
            blogService.deleteBlog(blogId);
            return ResponseEntity.ok("Blog deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting blog: " + e.getMessage());
        }
    }

    @GetMapping("/blogs")
    public ResponseEntity<?> getAllBlogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction) {

        Pageable pageable = PageRequest.of(
            page,
            size,
            Sort.by(Sort.Direction.fromString(direction), sort)
        );
        Page<Blog> blogs = blogService.getAllBlogs(pageable);
        return ResponseEntity.ok(blogs);
    }
}