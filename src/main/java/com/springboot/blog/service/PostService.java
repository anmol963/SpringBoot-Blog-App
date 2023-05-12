package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.dto.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto);
	
	List<PostDto> getAllPosts();
	
	PostDto getPostById(Long id);
	
	PostDto updatePost(PostDto postDto, long id);
	
	void deletePost(long id);
}
