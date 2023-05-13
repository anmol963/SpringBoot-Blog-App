package com.springboot.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.service.CommentService;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

	@Autowired
	private CommentService commentService;

	/*--------------- Comments Rest API methods----------------------*/

	// create comment rest api
	@PostMapping("/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable long postId, @RequestBody CommentDto commentDto){
		return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
	}
	
	// get all comments by post id 
	@GetMapping("{postId}/comments")
	public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable long postId){
		return ResponseEntity.ok(commentService.getAllCommentsByPostId(postId));
	}
	
	// get comment by comment id
	@GetMapping("{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable long postId, @PathVariable long commentId){
		return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
	}
	
	// update comment
	@PutMapping("{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable long postId,@PathVariable long commentId, @RequestBody CommentDto commentDto){
		return ResponseEntity.ok(commentService.updateComment(postId, commentId, commentDto));
	}
	
	// delete comment 
	@DeleteMapping("{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable long postId,@PathVariable long commentId){
		commentService.deleteComment(postId, commentId);
		String msg = "Comment deleted successfully";
		return ResponseEntity.ok(msg);
	}
}
