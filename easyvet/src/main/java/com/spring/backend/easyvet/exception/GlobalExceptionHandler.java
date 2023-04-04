package com.spring.backend.easyvet.exception;

import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
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
	
	@ExceptionHandler(ResponseStatusException.class)
	protected ResponseEntity<Object> handleEmptyValuesException(Exception ex, 
			WebRequest request) {
		return new ResponseEntity<>(ExceptionResponseDTO.builder().timestamp(LocalDateTime.now())
				.error("All the fields are required").message(Map.of("error", ex.getMessage()))
				.build(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<Object> handleResourceNotFoundException(Exception ex, 
			WebRequest request) {
		return new ResponseEntity<>(ExceptionResponseDTO.builder().timestamp(LocalDateTime.now())
				.error("The resource you are triying to find, was not found!!").message(Map.of("error", ex.getMessage()))
				.build(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex, 
                HttpServletRequest request, HttpServletResponse response) {
        if (ex instanceof NullPointerException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ExceptionResponseDTO.builder().timestamp(LocalDateTime.now())
				.error("Error detected!!").message(Map.of("error", ex.getMessage()))
				.build(), HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(MalformedURLException.class)
	protected ResponseEntity<Object> handleMalformedUrlException(MalformedURLException ex, 
			WebRequest request) {
		return new ResponseEntity<>(ExceptionResponseDTO.builder().timestamp(LocalDateTime.now())
				.error("The url you are triying to find, was not found!!").message(Map.of("error", ex.getMessage()))
				.build(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MessagingException.class)
	protected ResponseEntity<Object> handleMessaginException(MessagingException ex, 
			WebRequest request) {
		return new ResponseEntity<>(ExceptionResponseDTO.builder().timestamp(LocalDateTime.now())
				.error("Error on sending email").message(Map.of("error", ex.getMessage()))
				.build(), HttpStatus.BAD_REQUEST);
	}
}
