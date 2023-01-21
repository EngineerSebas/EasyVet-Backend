package com.spring.backend.easyvet.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.backend.easyvet.dto.ExceptionResponseDTO;

/**
 * Global Exception.
 * 
 * @author Andrés.
 */

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

		return new ResponseEntity<>(ExceptionResponseDTO.builder().timestamp(LocalDateTime.now())
				.error("blank required fields").message(Map.of("error", errors)).build(), 
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, 
			WebRequest request) {
		return new ResponseEntity<>(ExceptionResponseDTO.builder().timestamp(LocalDateTime.now())
				.error("entity not found").message(Map.of("error", ex.getMessage())).build(), 
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateKeyException.class)
	protected ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException ex, 
			WebRequest request) {
		return new ResponseEntity<>(ExceptionResponseDTO.builder().timestamp(LocalDateTime.now())
				.error("duplicate key value violates unique constraint").message(Map.of("error", ex.getMessage()))
				.build(), HttpStatus.BAD_REQUEST);
	}

}
