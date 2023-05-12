package com.springboot.blog.exceptioncontroller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class APIError {

	private String msg;
	private int error;
}
