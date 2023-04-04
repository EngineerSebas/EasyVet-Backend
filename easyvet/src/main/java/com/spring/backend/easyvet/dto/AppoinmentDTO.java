package com.spring.backend.easyvet.dto;

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

	@NotEmpty(message = "Name veterynary is required")
	private String name_veterynary;
	@NotEmpty(message = "Name propietor is required")
	private String name_propietor;
	@NotEmpty(message = "videocall meet link is required")
	private String videocall_meet;
	
	private EAppoinmentStatus appoinment_status;
	
	public AppoinmentDTO(@JsonProperty("id")Long id,
			@JsonProperty("propietor_id")Long propietor_id,
			@JsonProperty("veterynary_id")Long veterynary_id,
			@JsonProperty("type_appoinment")String type_appoinment,
			@JsonProperty("date_appoinment")Date date_appoinment, 
			@JsonProperty("rate_appoinment")Double rate_appoinment,
						 @JsonProperty("name_veterynary") String name_veterynary,
						 @JsonProperty("name_propietor") String name_propietor,
			@JsonProperty("videocall_meet") String videocall_meet,
			@JsonProperty("appoinment_status") EAppoinmentStatus appoinment_status) {
		this.id = id;
		this.propietor_id = propietor_id;
		this.veterynary_id = veterynary_id;
		this.type_appoinment = type_appoinment;
		this.date_appoinment = date_appoinment;
		this.rate_appoinment = rate_appoinment;
		this.videocall_meet = videocall_meet;
		this.name_veterynary = name_veterynary;
		this.name_propietor = name_propietor;
		this.appoinment_status = appoinment_status;
	}
	
}
