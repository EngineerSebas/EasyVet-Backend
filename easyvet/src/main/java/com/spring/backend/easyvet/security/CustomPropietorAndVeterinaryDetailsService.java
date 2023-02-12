package com.spring.backend.easyvet.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.backend.easyvet.model.entity.Propietor;
import com.spring.backend.easyvet.model.entity.Veterinary;
import com.spring.backend.easyvet.model.repository.IPropietorRepository;
import com.spring.backend.easyvet.model.repository.IVeterynaryRepository;

@Service
public class CustomPropietorAndVeterinaryDetailsService implements UserDetailsService {
	
	@Autowired
	private IPropietorRepository propietorRepository;
	
	@Autowired
	private IVeterynaryRepository veterinaryRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Propietor propietor = propietorRepository.findByEmail(email).orElse(null);
		Veterinary veterinary = veterinaryRepository.findByEmail(email).orElse(null);
		
		if (propietor != null) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(propietor.getRole().getName()));			
			return new User(propietor.getEmail(), propietor.getPassword(), authorities);
		}
	
		if (veterinary != null) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(veterinary.getRole().getName()));
			return new User(veterinary.getEmail(), veterinary.getPassword(), authorities);
		}

		throw new UsernameNotFoundException("Not found email " + email);
	}	
}
