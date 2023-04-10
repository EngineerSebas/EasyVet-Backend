package com.spring.backend.easyvet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.backend.easyvet.dto.SpecializationDTO;
import com.spring.backend.easyvet.model.entity.Specialization;
import com.spring.backend.easyvet.model.service.ISpecializationService;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class SpecializationController {

	@Autowired
	private ISpecializationService specializationService;
	
	@GetMapping(
		path = "/specializations",  
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<Specialization>> getAllSpecializations(){
		return new ResponseEntity<>(specializationService.findAllSpecializations(), HttpStatus.OK);
	}
	
	@GetMapping(
		path = "/specialization/{id}", 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
    public ResponseEntity<Specialization> getSpecializationById(@PathVariable("id") Long id){
        return new ResponseEntity<>(specializationService.findSpecializationById(id), HttpStatus.OK);
    }

	@PostMapping(
		path = "/specialization", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
    public ResponseEntity<Specialization> createSpecialization(@Valid @RequestBody SpecializationDTO specializationDTO){
		return new ResponseEntity<>(specializationService.createSpecialization(specializationDTO), HttpStatus.CREATED);
    }
	
	@PutMapping(
		path = "update-specialization/{id}", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
    public ResponseEntity<Specialization> updateSpecialization(
		@PathVariable(name = "id") Long id,
		@Valid @RequestBody SpecializationDTO specializationDTO){
        return new ResponseEntity<>(specializationService.updateSpecializationById(id, specializationDTO),HttpStatus.OK);
    }
	
	 @DeleteMapping("/delete-specialization/{id}")
	    public ResponseEntity<Object> deletePetById(@PathVariable("id") Long id){
	        specializationService.deleteSpecializationById(id);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
	
}
