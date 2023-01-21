package com.spring.backend.easyvet.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity User.
 * 
 * @author Andrés.
 */

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 50)
	private String last_name;

	@Column(nullable = false, length = 30)
	private String phone;

	@Column(nullable = false, length = 30)
	private String country;

	@Column(nullable = false, length = 50)
	private String city;

	@Column(nullable = false, length = 255, unique = true)
	private String email;

	@Column(nullable = false, length = 255)
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;
	
	public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
}
