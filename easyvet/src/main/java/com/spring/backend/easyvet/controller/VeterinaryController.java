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

import com.spring.backend.easyvet.dto.VeterinaryDTO;
import com.spring.backend.easyvet.dto.VeterinaryListDTO;
import com.spring.backend.easyvet.dto.VeterinaryStatusDTO;
import com.spring.backend.easyvet.dto.VeterinaryUpdateDTO;
import com.spring.backend.easyvet.model.repository.IVeterynaryRepository;
import com.spring.backend.easyvet.model.service.IVeterynaryService;
import com.spring.backend.easyvet.model.service.impl.EmailServiceImpl;
import com.spring.backend.easyvet.util.EVeterinaryStatus;

@RestController
@RequestMapping("/api/auth")
public class VeterinaryController {
	
	@Autowired
	private IVeterynaryRepository veterynaryRepository;
	
	@Autowired
	private IVeterynaryService veterynaryService;
	
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	@GetMapping(path = "/get-all-veterinaries",  produces = "application/json")
	public ResponseEntity<List<VeterinaryListDTO>> findAllVeterinaries() {
		return new ResponseEntity<>(veterynaryService.findAllVeterinaries(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/get-veterinary/{id}", produces = "application/json")
	public ResponseEntity<VeterinaryListDTO> findVeterinaryById(@PathVariable(name = "id")Long id) {
		return new ResponseEntity<>(veterynaryService.findVeterinaryById(id), HttpStatus.OK);
	}
	
	@GetMapping(path = "/get-veterinaries-status/{status}", produces = "application/json")
	public ResponseEntity<List<VeterinaryListDTO>> findVeterinariesByStatus(@PathVariable(name = "status")EVeterinaryStatus status) {
		return new ResponseEntity<>(veterynaryService.findVeterinariesByStatus(status), HttpStatus.OK);
	}
	
	@PostMapping(path = "/registerV", consumes = "application/json")
	public ResponseEntity<String> registerVeterinary(@RequestBody VeterinaryDTO veterinary) {
		
		if(veterynaryRepository.existsByEmail(veterinary.getEmail())) {
			return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
		}
		
		veterynaryService.registerVeterinary(veterinary);
		emailServiceImpl.sendWelcomeEmail(veterinary.getEmail(), veterinary.getName().concat(" " +veterinary.getLast_name()));
		
		return new ResponseEntity<>("Veterinary was register succesfully!!", HttpStatus.OK);
	}
	
	@PutMapping("/update-veterinary/{email}")
	public ResponseEntity<String> updateVeterinary(@PathVariable String email, @RequestBody VeterinaryUpdateDTO veterinaryUpdateDTO) {
		try {
			veterynaryService.updateVeterinary(email, veterinaryUpdateDTO, veterinaryUpdateDTO.getCurrentPassword());
			return new ResponseEntity<>("Veterinary was updated succesfully!!", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/update-status-veterinary/{email}")
	public ResponseEntity<String> updateStatusVeterinary(@PathVariable String email, @RequestBody VeterinaryStatusDTO veterinaryStatusDTO) {
		try {
			veterynaryService.updateVeterinaryStatusById(email, veterinaryStatusDTO);
			return new ResponseEntity<>("Veterinary status was updated succesfully!!", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
