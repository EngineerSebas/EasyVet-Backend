package com.spring.backend.easyvet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.spring.backend.easyvet.dto.PetDTO;
import com.spring.backend.easyvet.model.entity.Pet;
import com.spring.backend.easyvet.model.service.IPetService;


import lombok.RequiredArgsConstructor;

/**
 * Pet Controller.
 * 
 * @author Sebastian.
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {
	
	@Autowired
	private IPetService petService;

	@GetMapping(path = "/get-all-pets",  produces = "application/json")
	public ResponseEntity<List<Pet>> getAllPets(){
		return new ResponseEntity<>(petService.findAllPets(), HttpStatus.OK);
	}

    @PreAuthorize("hasRole('PROPIETOR')")
	@GetMapping(path = "/get-pet/{id}",produces = "application/json")
    public ResponseEntity<Pet> getPetById(@PathVariable("id") Long id){
        return new ResponseEntity<>(petService.findPetById(id), HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('PROPIETOR')")
	@GetMapping(path = "/get-pets-dni/{dni}",produces = "application/json")
    public ResponseEntity<List<Pet>> getPetById(@PathVariable("dni") String dni){
        return new ResponseEntity<>(petService.findPetsByDNI(dni), HttpStatus.OK);
    }

	@PreAuthorize("hasRole('PROPIETOR')")
    @PostMapping(path = "/create-pet", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Pet> createPet(@Valid @RequestBody PetDTO petDTO){
        return new ResponseEntity<>(petService.createPet(petDTO), HttpStatus.CREATED);
    }

	@PreAuthorize("hasRole('PROPIETOR')")
    @PutMapping(path = "/update-pet/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Pet> updatePet(@PathVariable(name = "id") Long id,
    		@Valid @RequestBody PetDTO petDTO){
        return new ResponseEntity<>(petService.updatePet(id, petDTO), HttpStatus.OK);
    }

	@PreAuthorize("hasRole('PROPIETOR')")
    @DeleteMapping("/delete-pet/{id}")
    public ResponseEntity<Object> deletePetById(@PathVariable("id") Long id){
        petService.deletePetById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
}
