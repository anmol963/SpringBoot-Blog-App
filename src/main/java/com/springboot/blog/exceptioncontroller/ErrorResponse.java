package com.springboot.blog.exceptioncontroller;

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
