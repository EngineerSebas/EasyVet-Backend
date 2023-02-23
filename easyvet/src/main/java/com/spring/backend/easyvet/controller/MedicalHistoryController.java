package com.spring.backend.easyvet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.backend.easyvet.dto.MedicalHistoryDTO;
import com.spring.backend.easyvet.model.entity.MedicalHistory;
import com.spring.backend.easyvet.model.service.IMedicalHistoryService;

@RestController
public class MedicalHistoryController {

	@Autowired
    private IMedicalHistoryService medicalHistoryService;
	
	@PreAuthorize("hasRole('VETERYNARY')")
	@GetMapping(path = "/medical-history/{petId}", produces = "application/json")
	public ResponseEntity<List<MedicalHistory>> getMedicalHistoryByPetId(@PathVariable(name = "petId") Long petId){
		return new ResponseEntity<>(medicalHistoryService.getMedicalHistoryByPetId(petId), HttpStatus.OK);
	}
	/*
	 * @PreAuthorize("hasRole('PROPIETOR')")
	@GetMapping(path = "/get-pets-dni/{dni}",produces = "application/json")
    public ResponseEntity<List<Pet>> getPetById(@PathVariable("dni") String dni){
        return new ResponseEntity<>(petService.findPetsByDNI(dni), HttpStatus.OK);
    }
	 * 
	 * */

	@PreAuthorize("hasRole('VETERYNARY')")
    @PostMapping("/pet-medical-history")
    public ResponseEntity<Void> createPetMedicalHistory(@RequestBody MedicalHistoryDTO medicalHistoryDTO) {
    	medicalHistoryService.savePetMedicalHistory(medicalHistoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
	@PreAuthorize("hasRole('VETERYNARY')")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePetMedicalHistoryById(@PathVariable Long id, @RequestBody MedicalHistoryDTO petMedicalHistoryDTO) {
        medicalHistoryService.updatePetMedicalHistoryById(id, petMedicalHistoryDTO);
        return ResponseEntity.ok().build();
    }
	
	
}
