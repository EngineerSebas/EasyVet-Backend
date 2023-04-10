package com.spring.backend.easyvet.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.backend.easyvet.util.EVeterinaryStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class VeterinaryDTO {

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
	
	@NotNull(message = "The role id is required")
	private Long role_id;
	
	@NotEmpty
	private String bank_account;
	
	@NotEmpty
	private String general_rate;
	
	@NotEmpty
	private String priority_rate;
	
	@NotEmpty
	private String type_bank;
	
	private Long specialization_id;
	
	private EVeterinaryStatus veterinary_status;
	
	public VeterinaryDTO(@JsonProperty("id")Long id,
			@JsonProperty("dni")String dni,
			@JsonProperty("name")String name,
			@JsonProperty("last_name")String last_name,
			@JsonProperty("phone")String phone, 
			@JsonProperty("country")String country,
			@JsonProperty("city") String city,
			@JsonProperty("email") String email,
			@JsonProperty("password") String password,
			@JsonProperty("role_id") Long role_id,
			@JsonProperty("bank_account") String bank_account,
			@JsonProperty("general_rate") String general_rate,
			@JsonProperty("priority_rate") String priority_rate,
			@JsonProperty("type_bank") String type_bank,
			@JsonProperty("specialization_id") Long specialization_id,
			@JsonProperty("veterinary_status") EVeterinaryStatus veterinary_status
			) {
		this.id = id;
		this.dni = dni;
		this.name = name;
		this.last_name = last_name;
		this.phone = phone;
		this.country = country;
		this.city = city;
		this.email = email;
		this.password = password;
		this.role_id = role_id;
		this.bank_account = bank_account;
		this.general_rate = general_rate;
		this.priority_rate = priority_rate;
		this.type_bank = type_bank;
		this.specialization_id = specialization_id;
		this.veterinary_status = veterinary_status;
	}
	
}
