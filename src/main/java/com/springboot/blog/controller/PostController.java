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

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.exception.PostAlreadyExistsException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	// create Post REST API method
	@PostMapping  // @RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) throws PostAlreadyExistsException{
		return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
	}
	
	
	// get all posts REST API
	@GetMapping
	public List<PostDto> getAllPosts(){
		return postService.getAllPosts();
	}
	
	// get post by id
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Long id) throws ResourceNotFoundException{
		return ResponseEntity.ok(postService.getPostById(id));
	}
	
	//update post by id
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable long id) throws ResourceNotFoundException, PostAlreadyExistsException{
		return ResponseEntity.ok(postService.updatePost(postDto, id));
	}
	
	// delete post by id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable long id){
		postService.deletePost(id);
		return ResponseEntity.ok("Post entity deleted successfully");
	}
}
