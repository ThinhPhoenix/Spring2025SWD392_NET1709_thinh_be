package com.fpt.swd392.cvsts.services;

import com.fpt.swd392.cvsts.entities.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlogService {
    Blog createBlog(Blog blog);

    Blog updateBlog(Blog blog);

    void deleteBlog(String blogId);

    Blog getBlogById(String blogId);

    Page<Blog> getAllBlogs(Pageable pageable);
}
