package com.spring.backend.easyvet.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter
public class JwtExceptionHandler extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	private String message;
	
}
