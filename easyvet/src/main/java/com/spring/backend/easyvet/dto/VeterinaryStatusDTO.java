package com.spring.backend.easyvet.dto;

import javax.validation.constraints.NotEmpty;

import com.spring.backend.easyvet.util.EVeterinaryStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VeterinaryStatusDTO {

	@NotEmpty(message = "status can not be empty")
	private EVeterinaryStatus veterinary_status;
	
}
