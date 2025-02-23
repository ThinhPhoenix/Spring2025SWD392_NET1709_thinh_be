package com.fpt.swd392.cvsts.services;

import com.fpt.swd392.cvsts.dto.BlogDTO;
import com.fpt.swd392.cvsts.entities.Blog;
import com.fpt.swd392.cvsts.utils.ListPageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBlogService {
    Blog createBlog(Blog blog);

    Blog updateBlog(Blog blog);

    void deleteBlog(String blogId);

    Blog getBlogById(String blogId);

    BlogDTO getBlogDTOById(String blogId);

    Page<Blog> getAllBlogs(Pageable pageable);

    ListPageable<List<BlogDTO>> getAllBlogDTOs(int page, int size, String sort, String direction);
}
