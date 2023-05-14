package com.springboot.blog.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

	private String resourceName;
    private String fieldName;
    private Long fieldValue;
}
