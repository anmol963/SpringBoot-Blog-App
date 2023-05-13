package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException{

	public BlogAPIException() {
		// TODO Auto-generated constructor stub
	}
	
	public BlogAPIException(String msg) {
		super(msg);
	}
}
