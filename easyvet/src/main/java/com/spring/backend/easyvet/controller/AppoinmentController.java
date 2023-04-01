package com.spring.backend.easyvet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.spring.backend.easyvet.dto.AppoinmentDTO;
import com.spring.backend.easyvet.model.entity.Appoinment;
import com.spring.backend.easyvet.model.service.IAppoinmentService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/appoinment")
public class AppoinmentController {
	
	@Autowired
	private IAppoinmentService appoinmentService;
	
	@GetMapping(path = "/get-all-appoinments",  produces = "application/json")
	public ResponseEntity<List<Appoinment>> getAllAppoinments(){
		return new ResponseEntity<>(appoinmentService.findAllAppoinments(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/get-appoinment/{id}",produces = "application/json")
    public ResponseEntity<Appoinment> getAppoinmentById(@PathVariable("id") Long id){
        return new ResponseEntity<>(appoinmentService.findAppoinmentById(id), HttpStatus.OK);
    }

	@GetMapping(path = "/get-appoinment-propietor/{id}",produces = "application/json")
	public ResponseEntity<List<Appoinment>> getAppoinmentByIdPropietor(@PathVariable("id") Long id){
		return new ResponseEntity<>(appoinmentService.findAllByPropietor_id(id), HttpStatus.OK);
	}

	@GetMapping(path = "/get-appoinment-veterynary/{id}",produces = "application/json")
	public ResponseEntity<List<Appoinment>> getAppoinmentByIdVeterynary(@PathVariable("id") Long id){
		return new ResponseEntity<>(appoinmentService.findAppoinmentByIdVeterynary(id), HttpStatus.OK);
	}


    @PostMapping(path = "/create-appoinment", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Appoinment> createPet(@Valid @RequestBody AppoinmentDTO appoinmentDTO){
        return new ResponseEntity<>(appoinmentService.registerAppoinment(appoinmentDTO), HttpStatus.CREATED);
    }
	
	@PutMapping(path = "/update-appoinment/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Appoinment> updateAppoinment(@PathVariable(name = "id") Long id,
    		@Valid @RequestBody AppoinmentDTO appoinmentDTO){
        return new ResponseEntity<>(appoinmentService.updateAppoinment(id, appoinmentDTO), HttpStatus.OK);
    }
	
	@DeleteMapping("/delete-appoinment/{id}")
    public ResponseEntity<Object> deleteAppoinmentById(@PathVariable("id") Long id){
		appoinmentService.deleteAppoinmentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('VETERYNARY')")
	@PutMapping("/veterinaries/{veterinaryId}/appointments/{appointmentId}/confirm/{confirmed}")
	public ResponseEntity<?> confirmAppointment(@PathVariable Long veterinaryId, @PathVariable Long appointmentId, @PathVariable Boolean confirmed) {
	    appoinmentService.confirmAppointment(veterinaryId, appointmentId,confirmed);
	    return ResponseEntity.ok().build();
	}
	
}
