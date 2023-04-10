package com.spring.backend.easyvet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.spring.backend.easyvet.dto.MedicalHistoryDTO;
import com.spring.backend.easyvet.model.entity.MedicalHistory;
import com.spring.backend.easyvet.model.service.IMedicalHistoryService;

@RestController
@CrossOrigin
public class MedicalHistoryController {

	@Autowired
    private IMedicalHistoryService medicalHistoryService;
	
	@GetMapping(
        path = "/medical-history/{petId}", 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
	public ResponseEntity<List<MedicalHistory>> getMedicalHistoryByPetId(@PathVariable(name = "petId") Long petId){
		return new ResponseEntity<>(medicalHistoryService.getMedicalHistoryByPetId(petId), HttpStatus.OK);
	}

    @PostMapping("/pet-medical-history/{petId}")
    public ResponseEntity<Void> createPetMedicalHistory(@PathVariable(name = "petId") Long petId, @RequestBody MedicalHistoryDTO medicalHistoryDTO) {
    	medicalHistoryService.savePetMedicalHistory(petId,medicalHistoryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
	@PreAuthorize("hasRole('VETERYNARY')")
    @PutMapping("/medical-history/{id}")
    public ResponseEntity<Void> updatePetMedicalHistoryById(@PathVariable Long id, @RequestBody MedicalHistoryDTO petMedicalHistoryDTO) {
        medicalHistoryService.updatePetMedicalHistoryById(id, petMedicalHistoryDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/medical-history/{id}")
    public ResponseEntity<String> deleteMedicalHistoryById(@PathVariable Long id) {
        medicalHistoryService.deleteMedicalHistoryById(id);
        return ResponseEntity.ok("Medical History with id " + id + " has been deleted successfully");
    }
	
}
