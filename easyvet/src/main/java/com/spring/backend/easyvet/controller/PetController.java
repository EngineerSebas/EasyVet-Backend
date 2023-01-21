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

import com.spring.backend.easyvet.dto.PetDTO;
import com.spring.backend.easyvet.model.entity.Pet;
import com.spring.backend.easyvet.model.service.IPetService;

import lombok.RequiredArgsConstructor;

/**
 * Pet Controller.
 * 
 * @author Andrés.
 */

@RestController
@RequestMapping("/v1/pets")
@RequiredArgsConstructor
public class PetController {
	
	@Autowired
	private IPetService petService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Pet>> getAllPets(){
		return new ResponseEntity<>(petService.findAllPets(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<Pet> getPetById(@PathVariable("id") Long id){
        return new ResponseEntity<>(petService.findPetById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Pet> createPet(@Valid @RequestBody PetDTO petDTO){
        return new ResponseEntity<>(petService.createPet(petDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Pet> updateCategory(@PathVariable(name = "id") Long id,
    		@Valid @RequestBody PetDTO petDTO){
        return new ResponseEntity<>(petService.updatePet(id, petDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategoryById(@PathVariable("id") Long id){
        petService.deletePetById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
}
