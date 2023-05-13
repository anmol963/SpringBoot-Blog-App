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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;
import com.springboot.blog.exception.PostAlreadyExistsException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;

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
	public PostResponse getAllPosts(@RequestParam(value="pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
									@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
									@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
									@RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir
	){
		return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
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
