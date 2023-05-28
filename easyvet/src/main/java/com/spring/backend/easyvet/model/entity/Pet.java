package com.spring.backend.easyvet.model.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity Role.
 *
 * @author Sebastian.
 */

@Entity
@Table(name = "pet")
@NoArgsConstructor @Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pet implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 80)
	private String name;
	
	@Column(nullable = false)
	private int age;
	
	@Column(nullable = false, length = 60)
	private String breed;

	@Column(nullable = false, length = 60)
	private String sex;

	@Column(nullable = false, length = 60)
	private String type;

	@Column(nullable = true, length = 255)
	private String img_profile;
	@JsonIgnore
	@Column(name = "propietor_id", nullable = false)
	private Long propietor_id;
	
	@JsonBackReference
	@ManyToOne(optional = false)
	@JoinColumn( name = "propietor_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Propietor propietor;
	
}
