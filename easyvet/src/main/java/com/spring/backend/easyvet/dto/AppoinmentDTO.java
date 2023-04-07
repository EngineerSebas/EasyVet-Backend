package com.spring.backend.easyvet.dto;

import java.time.LocalTime;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.backend.easyvet.util.EAppoinmentStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class AppoinmentDTO {
	
	private Long id;
	
	@NotEmpty(message = "type of appoinment is required")
	private String type_appoinment;
	
	@NotNull
	private Date date_appoinment;
	
	@NotNull
	private Double rate_appoinment;
	
	@NotEmpty(message = "Comentary is required")
	private String commentary;
	
	@NotEmpty(message = "videocall meet link is required")
	private String videocall_meet;
	
	private EAppoinmentStatus appoinment_status;
	
	public AppoinmentDTO(
			@JsonProperty("type_appoinment")String type_appoinment,
			@JsonProperty("date_appoinment")Date date_appoinment, 
			@JsonProperty("rate_appoinment")Double rate_appoinment,
			@JsonProperty("videocall_meet") String videocall_meet ) {
		//this.id = id;
		this.type_appoinment = type_appoinment;
		this.date_appoinment = date_appoinment;
		this.rate_appoinment = rate_appoinment;
		this.videocall_meet = videocall_meet;
	}
	
}
