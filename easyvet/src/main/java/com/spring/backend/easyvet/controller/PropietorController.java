package com.spring.backend.easyvet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.backend.easyvet.dto.PropietorListDTO;
import com.spring.backend.easyvet.dto.PropietorUpdateDTO;
import com.spring.backend.easyvet.model.entity.Propietor;
import com.spring.backend.easyvet.model.repository.IPropietorRepository;
import com.spring.backend.easyvet.model.service.IPropietorService;


@RestController
@RequestMapping("/api/auth")
public class PropietorController {
	
	@Autowired
	private IPropietorRepository propietorRepository;
	
	@Autowired
	private IPropietorService propietorService;
	
	@GetMapping(path = "/get-all-propietors",  produces = "application/json")
	public ResponseEntity<List<PropietorListDTO>> findAllPropietors() {
		return new ResponseEntity<>(propietorService.findAllPropietors(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/get-propietor/{id}", produces = "application/json")
	public ResponseEntity<PropietorListDTO> findVeterinaryById(@PathVariable(name = "id")Long id) {
		return new ResponseEntity<>(propietorService.findPropietorById(id), HttpStatus.OK);
	}
	
	@PostMapping(path = "/register", consumes = "application/json")
	public ResponseEntity<String> registerPropietor(@RequestBody Propietor propietor) {
		if(propietorRepository.existsByEmail(propietor.getEmail())) {
			return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
		}
		
		propietorService.registerPropietor(propietor);
		
		return new ResponseEntity<>("Propietor was register succesfully!!", HttpStatus.OK);
	}
	
	@PutMapping("/update-propietor/{email}")
	public ResponseEntity<String> updatePropietor(@PathVariable String email, @RequestBody PropietorUpdateDTO propietorUpdateDTO) {
		try {
			propietorService.updatePropietor(email, propietorUpdateDTO, propietorUpdateDTO.getCurrentPassword());
			return new ResponseEntity<>("Propietor was updated succesfully!!", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
