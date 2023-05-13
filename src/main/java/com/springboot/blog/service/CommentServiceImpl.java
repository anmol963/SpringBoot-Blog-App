package com.springboot.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.CommentRespository;
import com.springboot.blog.repository.PostRepository;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRespository commentRepository;
	@Autowired PostRepository postRepository;

	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) throws ResourceNotFoundException{
		Comment comment = dtoToEntity(commentDto);

		// retrieve post entity by id
		/* Optional<Post> optionalPost = postRepository.findById(postId);
		if(optionalPost.isEmpty()) {
			throw new ResourceNotFoundException("Post", "id", postId);
		}


		Post post = optionalPost.get(); */
		Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		comment.setPost(post);
		Comment savedComment = commentRepository.save(comment);
		return entityToDto(savedComment);
	}

	@Override
	public List<CommentDto> getAllCommentsByPostId(long postId){
		Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		// retreive comments by id
		List<Comment> comments = commentRepository.findByPostId(postId);
		List<CommentDto> commentDto = comments.stream().map(comment -> entityToDto(comment)).collect(Collectors.toList());
		return commentDto;
	}

	@Override
	public CommentDto getCommentById(long postId,long commentId) {
		Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));

		// retrieve comment by id
		Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentId));

		// checking if comment belongs to this post or not
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException();
		}
		return entityToDto(comment);
	}

	@Override
	public CommentDto updateComment(long postId, long commentId, CommentDto commentRequest) {
		Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));

		// retrieve comment by id
		Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentId));

		// checking if comment belongs to this post or not
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException();
		}
		comment.setBody(commentRequest.getBody());
		Comment updatedComment = commentRepository.save(comment);
		return entityToDto(updatedComment);
	}

	@Override
	public void deleteComment(long postId, long commentId) {
		Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));

		// retrieve comment by id
		Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentId));

		// checking if comment belongs to this post or not
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException();
		}
		commentRepository.deleteById(commentId);
	}

	private CommentDto entityToDto(Comment comment) {
		CommentDto commentDto = new CommentDto();
		commentDto.setId(comment.getId());
		commentDto.setName(comment.getName());
		commentDto.setEmail(comment.getEmail());
		commentDto.setBody(comment.getBody());
		return commentDto;
	}

	private Comment dtoToEntity(CommentDto commentDto) {
		Comment comment = new Comment();
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		return comment;
	}
}
