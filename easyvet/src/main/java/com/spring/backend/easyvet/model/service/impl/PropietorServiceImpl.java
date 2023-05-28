package com.spring.backend.easyvet.model.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.spring.backend.easyvet.dto.PropietorImgProfileDTO;
import com.spring.backend.easyvet.model.entity.Veterinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.spring.backend.easyvet.dto.PropietorListDTO;
import com.spring.backend.easyvet.dto.PropietorUpdateDTO;
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
	public PropietorListDTO findPropietorByEmail(String email) {
		PropietorListDTO propietorListDTO = propietorRepository.findPropietorByEmail(email);
        if(propietorListDTO == null){
            throw new EntityNotFoundException("the propietor with email " + email + " was not found");
        }
        return propietorListDTO; 
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<PropietorListDTO> findAllPropietors() {
		return propietorRepository.findAllPropietors();
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

	@Override
	public void updatePropietor(String email, PropietorUpdateDTO propietorUpdateDTO, String currentPassword) {

		Propietor user = propietorRepository.findByEmail(propietorUpdateDTO.getEmail()).get();
		
		try {
			if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
				throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Incorrect password");
			}
			
			user.setCity(propietorUpdateDTO.getCity());
			user.setCountry(propietorUpdateDTO.getCountry());
			user.setEmail(propietorUpdateDTO.getEmail());
			user.setLast_name(propietorUpdateDTO.getLast_name());
			user.setName(propietorUpdateDTO.getName());
			user.setPassword(passwordEncoder.encode(propietorUpdateDTO.getPassword()));
			user.setPhone(propietorUpdateDTO.getPhone());			
			user.setDni(propietorUpdateDTO.getDni());
			
			propietorRepository.save(user);
			
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST, 
					 "Required properties are missing");
		}
		
	}

	@Override
	@Transactional
	public void updateUserProfileImageByEmail(String email, PropietorImgProfileDTO propietorImgProfileDTO) {
		Propietor user = propietorRepository.findByEmail(email).orElseThrow(() ->
				new ResponseStatusException(HttpStatus.NOT_FOUND, "Propietor not found"));
		try {
			user.setImg_profile(propietorImgProfileDTO.getImg_profile());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Required properties are missing");
		}
	}
}
