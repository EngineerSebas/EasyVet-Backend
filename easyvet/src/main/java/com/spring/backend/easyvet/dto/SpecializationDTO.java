package com.spring.backend.easyvet.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class SpecializationDTO {
	
	@NotEmpty(message = "Specialization can not be empty")
	private String pet_specialization;
	
}
