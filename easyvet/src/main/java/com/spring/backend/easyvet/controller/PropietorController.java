package com.spring.backend.easyvet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.backend.easyvet.dto.PropietorListDTO;
import com.spring.backend.easyvet.dto.PropietorUpdateDTO;
import com.spring.backend.easyvet.model.entity.Propietor;
import com.spring.backend.easyvet.model.repository.IPropietorRepository;
import com.spring.backend.easyvet.model.service.IPropietorService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/auth")
public class PropietorController {
	
	@Autowired
	private IPropietorRepository propietorRepository;
	
	@Autowired
	private IPropietorService propietorService;
	
	@GetMapping(
		path = "/get-all-propietors",  
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<PropietorListDTO>> findAllPropietors() {
		return new ResponseEntity<>(propietorService.findAllPropietors(), HttpStatus.OK);
	}
	
	@GetMapping(
		path = "/get-propietor/{email}", 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<PropietorListDTO> findPropietorByEmail(@PathVariable(name = "email")String email) {
		return new ResponseEntity<>(propietorService.findPropietorByEmail(email), HttpStatus.OK);
	}
	
	@PostMapping(
		path = "/register", 
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<String> registerPropietor(@RequestBody Propietor propietor) {
		if(propietorRepository.existsByEmail(propietor.getEmail())) {
			return new ResponseEntity<>("El correo ya se encuentra registrado", HttpStatus.BAD_REQUEST);
		}
		propietorService.registerPropietor(propietor);
		return new ResponseEntity<>("Registro Existoso", HttpStatus.OK);
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
