package com.spring.backend.easyvet.dto;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Exception Class.
 *
 * @author Sebastian.
 */

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class ExceptionResponseDTO {
	private LocalDateTime timestamp;
    private String error;
    private Map<String, ?> message;
}
