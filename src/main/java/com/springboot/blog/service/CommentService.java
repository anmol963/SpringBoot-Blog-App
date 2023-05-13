package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.dto.CommentDto;

public interface CommentService {

	CommentDto createComment(long postId, CommentDto commentDto);
	
	List<CommentDto> getAllCommentsByPostId(long postId);
	
	CommentDto getCommentById(long postId,long commentId);
	
	CommentDto updateComment(long postId, long commentId, CommentDto commentRequest);
	
	void deleteComment(long postId, long commentId);
}
