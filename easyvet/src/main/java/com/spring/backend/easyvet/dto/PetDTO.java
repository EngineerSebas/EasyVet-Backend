package com.spring.backend.easyvet.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Pet DTO.
 * 
 * @author Andrés.
 */

@NoArgsConstructor @Getter @Setter
public class PetDTO {

	private Long id;
	
	@NotEmpty
	private String name;
	
	@Min(value=1, message="must be equal or greater than 1")  
    @Max(value=100, message="must be equal or less than 100")  
	private int age;
	
	@NotEmpty
	private String breed;

	@NotEmpty
	private String sex;
	
	private Long propietor_id;
	
    @JsonCreator
	public PetDTO(@JsonProperty("id")Long id, 
			@JsonProperty("name")String name,
			@JsonProperty("age")int age,
			@JsonProperty("breed")String breed, 
			@JsonProperty("sex")String sex,
			@JsonProperty("propietor_id") Long propietor_id) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.breed = breed;
		this.sex = sex;
		this.propietor_id = propietor_id;
	}
}
