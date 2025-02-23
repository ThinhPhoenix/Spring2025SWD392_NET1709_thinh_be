package com.fpt.swd392.cvsts.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fpt.swd392.cvsts.dto.BlogDTO;
import com.fpt.swd392.cvsts.entities.Blog;
import com.fpt.swd392.cvsts.services.IBlogService;
import com.fpt.swd392.cvsts.utils.ApiResponse;
import com.fpt.swd392.cvsts.utils.ListPageable;

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
    public ResponseEntity<ApiResponse<BlogDTO>> getBlogById(@RequestParam String blogId) {
        BlogDTO blogDTO = blogService.getBlogDTOById(blogId);
        ApiResponse<BlogDTO> response = new ApiResponse<>("200", blogDTO, "Blog retrieved successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
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
    public ResponseEntity<ApiResponse<List<BlogDTO>>> getAllBlogs(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "created_at") String sort,
            @RequestParam(defaultValue = "desc") String direction) {

        ListPageable<List<BlogDTO>> listPageable = blogService.getAllBlogDTOs(page, size, sort, direction);
        ApiResponse<List<BlogDTO>> response = new ApiResponse<>("200", listPageable.getList(), "Blogs retrieved successfully", listPageable.getPaging());
        return ResponseEntity.ok(response);
    }
}