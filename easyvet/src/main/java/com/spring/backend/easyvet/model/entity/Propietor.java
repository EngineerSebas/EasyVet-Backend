package com.spring.backend.easyvet.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity Role.
 * 
 * @author Andrés.
 */

@Entity
@Table(name = "propietor")
@NoArgsConstructor
@Getter
@Setter
public class Propietor extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "propietor", fetch = FetchType.LAZY)
	private Set<Pet> pets = new HashSet<>(); 
	
	public Propietor(String email, String password) {
        super(email, password);
    }
	
}
