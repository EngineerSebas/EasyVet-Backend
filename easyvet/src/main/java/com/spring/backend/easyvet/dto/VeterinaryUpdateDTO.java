package com.spring.backend.easyvet.dto;

import javax.validation.constraints.NotEmpty;

import com.spring.backend.easyvet.util.EVeterinaryStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VeterinaryUpdateDTO {
	
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
	
	@NotEmpty
	private String bank_account;
	
	@NotEmpty
	private String general_rate;
	
	@NotEmpty
	private String priority_rate;
	
	@NotEmpty
	private String type_bank;

	private Long specialization_id;

	private String currentPassword;
	
	private EVeterinaryStatus veterinary_status;
	
}
