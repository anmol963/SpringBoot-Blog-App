package com.springboot.blog.exceptioncontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.PostAlreadyExistsException;
import com.springboot.blog.exception.ResourceNotFoundException;

@RestControllerAdvice  // acts as a catch block
public class GlobalExceptionHandler {

	/* ------------------- Posts Exceptions ----------------- */
	
	@ExceptionHandler(PostAlreadyExistsException.class)
	public ResponseEntity<APIError> postAlreadyExistsException(Exception e){
		APIError error = new APIError("Post with given title already exists", 400);
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String>  ResourceNotFoundExceptionHandler(ResourceNotFoundException e) {
		String response = String.format("%s not found with %s : %s",e.getResourceName(),e.getFieldName(),e.getFieldValue());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	/*--------------------- Comment Exceptions-----------------*/
	
	@ExceptionHandler(BlogAPIException.class)
	public ResponseEntity<APIError> blogApiExceptionHandler(Exception e){
		APIError error = new APIError("Comment does not belong to this post", 400);
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
}
