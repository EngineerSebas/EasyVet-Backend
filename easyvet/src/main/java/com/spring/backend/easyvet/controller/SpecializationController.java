package com.spring.backend.easyvet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.backend.easyvet.dto.SpecializationDTO;
import com.spring.backend.easyvet.model.entity.Specialization;
import com.spring.backend.easyvet.model.service.ISpecializationService;

@RestController
@RequestMapping("/api/")
public class SpecializationController {

	@Autowired
	private ISpecializationService specializationService;
	
	@GetMapping(path = "/specializations",  produces = "application/json")
	public ResponseEntity<List<Specialization>> getAllSpecializations(){
		return new ResponseEntity<>(specializationService.findAllSpecializations(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/specialization/{id}",produces = "application/json")
    public ResponseEntity<Specialization> getSpecializationById(@PathVariable("id") Long id){
        return new ResponseEntity<>(specializationService.findSpecializationById(id), HttpStatus.OK);
    }

	@PostMapping(path = "/specialization",  consumes = "application/json", produces = "application/json")
    public ResponseEntity<Specialization> createSpecialization(@Valid @RequestBody SpecializationDTO specializationDTO){
		return new ResponseEntity<>(specializationService.createSpecialization(specializationDTO), HttpStatus.CREATED);
    }
	
	@PutMapping(path = "update-specialization/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Specialization> updateSpecialization(@PathVariable(name = "id") Long id,
    		@Valid @RequestBody SpecializationDTO specializationDTO){
        return new ResponseEntity<>(specializationService.updateSpecializationById(id, specializationDTO),HttpStatus.OK);
    }
	
	 @DeleteMapping("/delete-specialization/{id}")
	    public ResponseEntity<Object> deletePetById(@PathVariable("id") Long id){
	        specializationService.deleteSpecializationById(id);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
	
}
