package com.fpt.swd392.cvsts.repositories;

import com.fpt.swd392.cvsts.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {

}