package com.spring.backend.easyvet.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.backend.easyvet.dto.VeterinaryDTO;
import com.spring.backend.easyvet.dto.VeterinaryListDTO;
import com.spring.backend.easyvet.dto.VeterinaryStatusDTO;
import com.spring.backend.easyvet.dto.VeterinaryUpdateDTO;
import com.spring.backend.easyvet.model.repository.IVeterynaryRepository;
import com.spring.backend.easyvet.model.service.IVeterynaryService;
import com.spring.backend.easyvet.model.service.impl.EmailServiceImpl;
import com.spring.backend.easyvet.util.EVeterinaryStatus;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class VeterinaryController {
	
	@Autowired
	private IVeterynaryRepository veterynaryRepository;
	
	@Autowired
	private IVeterynaryService veterynaryService;
	
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	@GetMapping(
		path = "/get-all-veterinaries",  
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<VeterinaryListDTO>> findAllVeterinaries() {
		return new ResponseEntity<>(veterynaryService.findAllVeterinaries(), HttpStatus.OK);
	}
	
	@GetMapping(
		path = "/get-veterinary/{email}", 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<VeterinaryListDTO> findVeterinaryById(@PathVariable(name = "email")String email) {
		return new ResponseEntity<>(veterynaryService.findVeterinaryByEmail(email), HttpStatus.OK);
	}
	
	@GetMapping(
		path = "/get-veterinaries-status/{status}", 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<VeterinaryListDTO>> findVeterinariesByStatus(@PathVariable(name = "status")EVeterinaryStatus status) {
		return new ResponseEntity<>(veterynaryService.findVeterinariesByStatus(status), HttpStatus.OK);
	}

	@PostMapping(
		path = "/registerV", 
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<String> registerVeterinary(
		@RequestBody VeterinaryDTO veterinary,  
        @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime start, 
        @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime end) {
									
		if(veterynaryRepository.existsByEmail(veterinary.getEmail())) {
			return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
		}
		
		veterynaryService.registerVeterinary(veterinary, start, end, true);
		emailServiceImpl.sendWelcomeEmail(veterinary.getEmail(), veterinary.getName().concat(" " +veterinary.getLast_name()));
		
		return new ResponseEntity<>("Veterinary was registered successfully!!", HttpStatus.OK);
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

	@PutMapping("/update-schdule-veterinary/{id}")
	public ResponseEntity<String> updateVeterinarySchedule(
		@PathVariable Long id, 
		@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime start, 
        @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime end) {
		try {
			veterynaryService.updateVeterinaryScheduleById(id, start, end, true);
			return new ResponseEntity<>("Veterinary schedule was updated succesfully!!", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
