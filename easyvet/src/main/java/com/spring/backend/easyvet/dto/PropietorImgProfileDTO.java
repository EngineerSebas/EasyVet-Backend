package com.spring.backend.easyvet.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class PropietorImgProfileDTO {

	@NotEmpty(message = "img profile cant be empty")
	private String img_profile;
	
}
