package com.springboot.blog.exception;

public class PostAlreadyExistsException extends RuntimeException{

	public PostAlreadyExistsException() {
		// TODO Auto-generated constructor stub
	}
	
	public PostAlreadyExistsException(String msg) {
		super(msg);
	}
}
