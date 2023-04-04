package com.spring.backend.easyvet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtAuthResponseDTO {

	private String accessToken;
	private String tokenType = "Bearer";
	
	public JwtAuthResponseDTO(String accessToken) {
		super();
		this.accessToken = accessToken;
	}
	
	public JwtAuthResponseDTO(String accessToken, String tokenType) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
	}
}
