package com.springboot.blog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.PostAlreadyExistsException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.PostRepository;

@Service
// available for auto detection while component scanning
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public PostDto createPost(PostDto postDto) throws PostAlreadyExistsException{
		
		// Check if a post with the same title already exists
		if(postRepository.existsByTitle(postDto.getTitle())) {
			throw new PostAlreadyExistsException();
		}
		
		// convert DTO to Entity
		Post post = dtoToEntity(postDto);
		Post savedPost = postRepository.save(post);
		
		// convert Entity to DTO
		PostDto postResponse = entityToDto(savedPost);
		return postResponse;
	}

	@Override
	public List<PostDto> getAllPosts() {
		List<Post> list = postRepository.findAll();
		List<PostDto> listDto = new ArrayList<>();
		
		list.forEach(p->listDto.add(entityToDto(p)));
		// List<PostDto> listDto = list.stream().map(post->entityToDto(post)).collect(Collectors.toList());
		return listDto;
	}
	
	@Override
	public PostDto getPostById(Long id) throws ResourceNotFoundException{
		
		// Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		
		Optional<Post> optionalPost = postRepository.findById(id);
		if(optionalPost.isEmpty()) {
			throw new ResourceNotFoundException("Post", "id", id);
		}
		
		Post post = optionalPost.get();
		return entityToDto(post);
	}
	
	@Override
	public PostDto updatePost(PostDto postDto, long id) throws PostAlreadyExistsException, ResourceNotFoundException{
		Optional<Post> optionalPost = postRepository.findById(id);
		if(optionalPost.isEmpty()) {
			throw new ResourceNotFoundException("Post", "id", id);
		}
		
		Post post = optionalPost.get();
		
		if(postRepository.existsByTitle(postDto.getTitle()) && !postDto.getTitle().equals(post.getTitle())) {
			throw new PostAlreadyExistsException();
		}
		
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post updatedPost = postRepository.save(post);
		return entityToDto(updatedPost);
	}
	
	@Override
	public void deletePost(long id) throws ResourceNotFoundException{
		Optional<Post> optionalPost = postRepository.findById(id);
		if(optionalPost.isEmpty()) {
			throw new ResourceNotFoundException("Post", "id", id);
		}
		postRepository.delete(optionalPost.get());
	}
	
	// mapping entity to DTO
	private PostDto entityToDto(Post post) {
		PostDto postResponse = new PostDto();
		postResponse.setId(post.getId());
		postResponse.setTitle(post.getTitle());
		postResponse.setDescription(post.getDescription());
		postResponse.setContent(post.getContent());
		return postResponse;
	}
	
	// mapping DTO to entity
	private Post dtoToEntity(PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		return post;
	}
}