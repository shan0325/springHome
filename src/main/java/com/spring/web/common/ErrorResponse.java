package com.spring.web.common;

import java.util.List;

import org.springframework.validation.FieldError;

import lombok.Data;

@Data
public class ErrorResponse {
	
	public static final String MESSAGE_BAD_REQUEST = "잘못된 요청입니다.";
	public static final String CODE_BAD_REQUEST = "Bad.Request";

	private String message;
	private String code;
	private List<FieldError> errors;
	
}
