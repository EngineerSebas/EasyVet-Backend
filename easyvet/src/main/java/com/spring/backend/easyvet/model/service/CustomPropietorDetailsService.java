package com.spring.backend.easyvet.model.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.backend.easyvet.model.entity.Propietor;
import com.spring.backend.easyvet.model.entity.Role;
import com.spring.backend.easyvet.model.repository.IPropietorRepository;

/*
public class CustomPropietorDetailsService implements UserDetailsService {

	/*
	@Autowired
	private IPropietorRepository propietorRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	
		Propietor propietor = propietorRepository.findByEmail(email).orElseThrow(() -> 
				new UsernameNotFoundException("User with email " + email + " was not found" ));
		
		Role role = propietor.getRole();
		
		return new User(propietor.getEmail(), propietor.getPassword(),
				Collections.singletonList(new SimpleGrantedAuthority(role.getName())));
		
	}

}*/
