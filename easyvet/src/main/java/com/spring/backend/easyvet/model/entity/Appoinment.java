package com.spring.backend.easyvet.model.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.backend.easyvet.util.EAppoinmentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "appoinment")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Appoinment implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonBackReference
	@ManyToOne(optional = false)
	@JoinColumn( name = "propietor_id", referencedColumnName = "id")
	private Propietor propietor;	
	
	@JsonBackReference
	@ManyToOne(optional = false)
	@JoinColumn( name = "veterynary_id", referencedColumnName = "id")
	private Veterinary veterynary;
	
	@Column(nullable = false, length = 80)
	private String type_appoinment;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_appoinment;
	
	@Range(min = 0)
	private Double rate_appoinment;
	
	@Column(nullable = false, length = 256)
	private String commentary;
	
	@Column(nullable = false, length = 256)
	private String videocall_meet;

	@Column(nullable = false, length = 256)
	private String name_veterynary;

	@Column(nullable = false, length = 256)
	private String name_propietor;

	@Column(nullable = false, length = 256)
	private String name_pet;

	@Column(nullable = false, length = 256)
	private String type_pet;
	@Column(nullable = false, length = 256)
	private String img_profile_pet;

	@Enumerated(EnumType.STRING)
    @Column(name = "appoinment_status", nullable = false)
    private EAppoinmentStatus appoinment_status;

	@Column(name = "appoinment_hour", nullable = false)
	private LocalTime appoinmentHour;
}
