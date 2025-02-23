package com.fpt.swd392.cvsts.repositories;

import com.fpt.swd392.cvsts.entities.Blog;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {

    @Query(value = """
            select 
				b.id as blog_id,
  				b.title,
  				b.short_description,
  				b.content,
  				b.image_url,
  				b.status as blog_status,
  				b.created_at,
  				b.updated_at,
  				b.published_at,
  				b.author_id,
  				au.fullname as author_fullname,
  				au.email as author_email,
  				au.avatar_url as author_avatar_url,
  				b.reviewer_id,
  				rv.fullname as reviewer_fullname,
  				rv.email as reviewer_email,
  				rv.avatar_url as reviewer_avatar_url
			from blogs b
			inner join users au on b.author_id = au.id
			left join users rv on b.reviewer_id = rv.id
			where b.id = :blogId 
      			and (au.deleted_at is null and au.banned_at is null)
                """, nativeQuery = true)
    List<Object[]> getBlogById(@Param("blogId") String blogId);

	@Query(value = """
		select 
			b.id as blog_id,
  			b.title,
			b.short_description,
  			b.content,
  			b.image_url,
  			b.status as blog_status,
  			b.created_at,
  			b.updated_at,
  			b.published_at,
  			b.author_id,
  			au.fullname as author_fullname,
  			au.email as author_email,
  			au.avatar_url as author_avatar_url,
  			b.reviewer_id,
  			rv.fullname as reviewer_fullname,
  			rv.email as reviewer_email,
  			rv.avatar_url as reviewer_avatar_url
		from blogs b
		inner join users au on b.author_id = au.id
		left join users rv on b.reviewer_id = rv.id
		where au.deleted_at is null and au.banned_at is null
		ORDER BY :order
		LIMIT :limit
		OFFSET :offset
			""",
			nativeQuery = true)
	List<Object[]> getAllBlogs(@Param("limit") int limit, @Param("offset") int offset, @Param("order") String order);

	@Query(value = """
			select count(*)
  			from blogs b
			inner join users au on b.author_id = au.id
			left join users rv on b.reviewer_id = rv.id
			where (au.deleted_at is null and au.banned_at is null)
			""",
			nativeQuery = true)
	int countAllBlogs();
}