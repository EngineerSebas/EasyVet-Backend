package com.spring.backend.easyvet.model.service.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.spring.backend.easyvet.model.entity.Propietor;
import com.spring.backend.easyvet.model.entity.Role;
import com.spring.backend.easyvet.model.repository.IPropietorRepository;
import com.spring.backend.easyvet.model.repository.IRoleRepository;
import com.spring.backend.easyvet.model.service.IPropietorService;

@Service
public class PropietorServiceImpl implements IPropietorService{

	@Autowired
	private IPropietorRepository propietorRepository;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional(readOnly = true)
	public Propietor findPropietorById(Long id) {
		Propietor propietor = propietorRepository.findPropietorById(id);
        if(propietor == null){
            throw new EntityNotFoundException("the propietor with id " + id + " was not found");
        }
        return propietor; 
	}

	@Override
	@Transactional
	public void registerPropietor(Propietor propietor) {
		
		Propietor user = new Propietor();
		
		try {
			user.setCity(propietor.getCity());
			user.setDni(propietor.getDni());
			user.setCountry(propietor.getCountry());
			user.setEmail(propietor.getEmail());
			user.setLast_name(propietor.getLast_name());
			user.setName(propietor.getName());
			user.setPassword(passwordEncoder.encode(propietor.getPassword()));
			user.setPhone(propietor.getPhone());
			
			Role role = roleRepository.findByName("ROLE_PROPIETOR").get();
			user.setRole(role);
			
			propietorRepository.save(user);
		} catch (Exception e) {
			 throw new ResponseStatusException( HttpStatus.BAD_REQUEST, 
					 "Required properties are missing");
		}
	}


}
