package com.spring.backend.easyvet.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class AppoinmentDTO {
	
	private Long id;
	
	private Long propietor_id;	
	
	private Long veterynary_id;
	
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
	
	public AppoinmentDTO(@JsonProperty("id")Long id,
			@JsonProperty("propietor_id")Long propietor_id,
			@JsonProperty("veterynary_id")Long veterynary_id,
			@JsonProperty("type_appoinment")String type_appoinment,
			@JsonProperty("date_appoinment")Date date_appoinment, 
			@JsonProperty("rate_appoinment")Double rate_appoinment,
			@JsonProperty("videocall_meet") String videocall_meet) {
		this.id = id;
		this.propietor_id = propietor_id;
		this.veterynary_id = veterynary_id;
		this.type_appoinment = type_appoinment;
		this.date_appoinment = date_appoinment;
		this.rate_appoinment = rate_appoinment;
		this.videocall_meet = videocall_meet;
	}
	
}
