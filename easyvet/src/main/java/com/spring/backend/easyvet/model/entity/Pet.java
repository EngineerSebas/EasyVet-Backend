package com.spring.backend.easyvet.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity Role.
 * 
 * @author Andrés.
 */

@Entity
@Table(name = "pet")
@NoArgsConstructor @Getter @Setter
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

	@JsonIgnore
	@ManyToMany(mappedBy = "pets")
	Set<Propietor> propietors = new HashSet<>();
	
}
