package com.spring.backend.easyvet.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity Role.
 * 
 * @author Andrés.
 */

@Entity
@Table(name = "specialization")
@NoArgsConstructor
@Getter
@Setter
public class Specialization implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 60)
	private String pet_specialization;
}
