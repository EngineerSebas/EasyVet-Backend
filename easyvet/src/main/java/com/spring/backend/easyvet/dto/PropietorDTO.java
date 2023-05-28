package com.spring.backend.easyvet.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class PropietorDTO {

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

	private String img_profile;
	@NotNull(message = "The role id is required")
	private Long role_id;
	
	@JsonCreator
	public PropietorDTO(@JsonProperty("id")Long id,
			@JsonProperty("dni")String dni,
			@JsonProperty("name")String name,
			@JsonProperty("last_name")String last_name,
			@JsonProperty("phone")String phone, 
			@JsonProperty("country")String country,
			@JsonProperty("city") String city,
			@JsonProperty("email") String email,
			@JsonProperty("password") String password,
			@JsonProperty("img_profile") String img_profile,
			@JsonProperty("role_id") Long role_id) {
		this.id = id;
		this.dni = dni;
		this.name = name;
		this.last_name = last_name;
		this.phone = phone;
		this.country = country;
		this.email = email;
		this.password = password;
		this.role_id = role_id;
		this.img_profile = img_profile;
	}
	
}
