package com.spring.backend.easyvet.dto;

import com.spring.backend.easyvet.util.EVeterinaryStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class VeterinaryListDTO {
	private Long id;
	private String dni;
	private String name;
	private String last_name;
	private String phone;
	private String country;
	private String city;
	private String email;
	private String bank_account;
	private String general_rate;
	private String priority_rate;
	private String type_bank;
	private Long specialization_id;
	private EVeterinaryStatus veterinary_status;
	
}
