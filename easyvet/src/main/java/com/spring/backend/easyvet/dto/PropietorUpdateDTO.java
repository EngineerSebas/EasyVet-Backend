package com.spring.backend.easyvet.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PropietorUpdateDTO {

	private Long id;

	@NotEmpty
	private String dni;
	
	@NotEmpty
	private String name;

	@NotEmpty
	private String last_name;

	@NotEmpty
	private String phone;

	@NotEmpty
	private String country;

	@NotEmpty
	private String city;

	@NotEmpty
	private String email;

	@NotEmpty
	private String password;
	
	private String currentPassword;
	
}
