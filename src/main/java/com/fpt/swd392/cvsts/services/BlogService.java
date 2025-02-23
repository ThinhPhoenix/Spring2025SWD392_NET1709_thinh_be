package com.fpt.swd392.cvsts.services;

import com.fpt.swd392.cvsts.dto.BlogDTO;
import com.fpt.swd392.cvsts.dto.TemplateDoseIntervalDTO;
import com.fpt.swd392.cvsts.entities.Blog;
import com.fpt.swd392.cvsts.repositories.BlogRepository;
import com.fpt.swd392.cvsts.utils.ListPageable;
import com.fpt.swd392.cvsts.utils.Paging;
import com.fpt.swd392.cvsts.utils.Utils;

import ch.qos.logback.classic.pattern.Util;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public BlogDTO getBlogDTOById(String blogId) {
        try {
            List<Object[]> results = blogRepository.getBlogById(blogId);
            List<BlogDTO> blogDTOs = convertToBlogDTO(results);
            if (blogDTOs.isEmpty()) {
                throw new ResourceNotFoundException("Blog not found with ID: " + blogId);
            }
            return blogDTOs.get(0);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while getting blog by ID: " + e.getMessage());
        }
    }

    public List<BlogDTO> convertToBlogDTO(List<Object[]> results) {
        List<BlogDTO> blogDTOs = results.stream().map(r -> {
            BlogDTO blogDTO = new BlogDTO();
            blogDTO.setBlogId(r[0].toString());
            blogDTO.setTitle(r[1].toString());
            blogDTO.setShortDescription(r[2].toString());
            blogDTO.setContent(r[3].toString());
            blogDTO.setImageUrl(r[4] != null ? r[4].toString() : null);
            blogDTO.setBlogStatus(r[5].toString());
            if (r[6] != null) {
                java.sql.Timestamp sqlDateTime = (java.sql.Timestamp) r[6];
                blogDTO.setCreatedAt(sqlDateTime.toLocalDateTime());
            }
            if (r[7] != null) {
                java.sql.Timestamp sqlDateTime = (java.sql.Timestamp) r[7];
                blogDTO.setUpdatedAt(sqlDateTime.toLocalDateTime());
            }
            if (r[8] != null) {
                java.sql.Timestamp sqlDateTime = (java.sql.Timestamp) r[8];
                blogDTO.setPublishedAt(sqlDateTime.toLocalDateTime());
            }
            blogDTO.setAuthorId(r[9].toString());
            blogDTO.setAuthorFullName(r[10].toString());
            blogDTO.setAuthorEmail(r[11].toString());
            blogDTO.setAuthorAvatarUrl(r[12] != null ? r[12].toString() : null);
            blogDTO.setReviewerId(r[13] != null ? r[13].toString() : null);
            blogDTO.setReviewerFullName(r[14] != null ? r[14].toString() : null);
            blogDTO.setReviewerEmail(r[15] != null ? r[15].toString() : null);
            blogDTO.setReviewerAvatarUrl(r[16] != null ? r[16].toString() : null);
            return blogDTO;
        }).collect(Collectors.toList());
        return blogDTOs;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Blog> getAllBlogs(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Blog getBlogById(String blogId) {
        return blogRepository.findById(blogId)
            .orElseThrow(() -> new RuntimeException("Blog not found with ID: " + blogId));
    }

    @Override
    public ListPageable<List<BlogDTO>> getAllBlogDTOs(int page, int size, String sort, String direction) {
        int offset = (page - 1) * size;
        String sorting = "b." + sort;
        String order = sorting + " " + direction;
        List<Object[]> results = blogRepository.getAllBlogs(size, offset, order);
        List<BlogDTO> blogDTOs = convertToBlogDTO(results);
        int totalPages = Utils.calculateTotalPage(blogRepository.countAllBlogs(), size);
        ListPageable<List<BlogDTO>> listPageable = new ListPageable<>(blogDTOs, new Paging(page, size, totalPages));
        return listPageable;
    }
}