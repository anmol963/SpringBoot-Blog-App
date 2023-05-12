package com.springboot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.springboot.blog.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

	boolean  existsByTitle(String title);
	
	Post findByTitle(String title);
	
}
