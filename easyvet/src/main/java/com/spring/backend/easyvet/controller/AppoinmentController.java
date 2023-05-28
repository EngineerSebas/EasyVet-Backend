package com.spring.backend.easyvet.controller;

import java.time.LocalTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.spring.backend.easyvet.dto.AppoinmentDTO;
import com.spring.backend.easyvet.model.entity.Appoinment;
import com.spring.backend.easyvet.model.service.IAppoinmentService;

@RestController
@CrossOrigin
@RequestMapping("/appoinment")
public class AppoinmentController {
	
	@Autowired
	private IAppoinmentService appoinmentService;
	
	@GetMapping(
		path = "/get-all-appoinments",  
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<Appoinment>> getAllAppoinments(){
		return new ResponseEntity<>(appoinmentService.findAllAppoinments(), HttpStatus.OK);
	}
	
	@GetMapping(
		path = "/get-appoinment/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
    public ResponseEntity<Appoinment> getAppoinmentById(@PathVariable("id") Long id){
        return new ResponseEntity<>(appoinmentService.findAppoinmentById(id), HttpStatus.OK);
    }

	@GetMapping(
		path = "/get-appoinment-propietor/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<Appoinment>> getAppoinmentByIdPropietor(@PathVariable("id") Long id){
		return new ResponseEntity<>(appoinmentService.findAllByPropietor_id(id), HttpStatus.OK);
	}

	@GetMapping(
		path = "/get-appoinment-veterynary/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<Appoinment>> getAppoinmentByIdVeterynary(@PathVariable("id") Long id){
		return new ResponseEntity<>(appoinmentService.findAppoinmentByIdVeterynary(id), HttpStatus.OK);
	}

    @PostMapping(
		path = "/create-appoinment/{propietorId}/{veterinaryId}", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
    public ResponseEntity<Appoinment> createAppoinment(
	@PathVariable("propietorId") Long propietorId,
	@PathVariable("veterinaryId") Long veterinaryId,
	@Valid @RequestBody AppoinmentDTO appoinmentDTO,
	@RequestParam("appoinmentHour") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime appoinmentHour){
        return new ResponseEntity<>(appoinmentService.registerAppoinment(propietorId,veterinaryId, appoinmentDTO, appoinmentHour), HttpStatus.CREATED);
    }
	
	@PutMapping(
		path = "/update-appoinment/{appoinmentId}/{propietorId}/{veterinaryId}", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
    public ResponseEntity<Appoinment> updateAppoinment(
		@PathVariable(name = "appoinmentId") Long appoinmentId,
		@PathVariable(name = "propietorId") Long propietorId,
		@PathVariable(name = "veterinaryId") Long veterinaryId,
    	@Valid @RequestBody AppoinmentDTO appoinmentDTO,
		@RequestParam("appoinmentHour") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime appoinmentHour){
        return new ResponseEntity<>(appoinmentService.updateAppoinment(appoinmentId,propietorId,veterinaryId,appoinmentDTO, appoinmentHour), HttpStatus.OK);
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

	@GetMapping("/AllAppoinmentsWithPropietorAndVeterynary")
	public List<Object[]> getAppoinmentsWithPropietorAndVeterynary() {
		return appoinmentService.getAppoinmentsWithPropietorAndVeterynary();
	}
	
}
