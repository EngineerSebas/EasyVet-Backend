package com.spring.backend.easyvet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.backend.easyvet.model.entity.Propietor;
import com.spring.backend.easyvet.model.repository.IPropietorRepository;
import com.spring.backend.easyvet.model.service.IPropietorService;

@RestController
@RequestMapping("/propietor")
public class PropietorController {
	
	@Autowired
	private IPropietorRepository propietorRepository;
	
	@Autowired
	private IPropietorService propietorService;
	
	@PostMapping(path = "/register", consumes = "application/json")
	public ResponseEntity<String> registerPropietor(@RequestBody Propietor propietor) {
		if(propietorRepository.existsByEmail(propietor.getEmail())) {
			return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
		}
		
		propietorService.registerPropietor(propietor);
		
		return new ResponseEntity<>("Propietor was register succesfully!!", HttpStatus.OK);
	}
	

	/*@PostMapping(path = "/register", consumes = "application/json")
	public ResponseEntity<String> registerPropietor(@RequestBody Propietor propietor) {
		
		if(propietorRepository.existsByEmail(propietor.getEmail())) {
			return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
		}
		
		Propietor user = new Propietor();
		
		user.setCity(propietor.getCity());
		user.setCountry(propietor.getCountry());
		user.setEmail(propietor.getEmail());
		user.setLast_name(propietor.getLast_name());
		user.setName(propietor.getName());
		user.setPassword(passwordEncoder.encode(propietor.getPassword()));
		user.setPhone(propietor.getPhone());
		
		Role role = roleRepository.findByName("ROLE_PROPIETOR").get();
		user.setRole(role);
		
		propietorRepository.save(user);
		
		return new ResponseEntity<>("Propietor was register succesfully!!", HttpStatus.OK);
	}*/

	
	
}
