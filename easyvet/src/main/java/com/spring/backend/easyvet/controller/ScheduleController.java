package com.spring.backend.easyvet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.backend.easyvet.model.entity.Schedule;
import com.spring.backend.easyvet.model.service.IScheduleService;

@RestController
@RequestMapping("/api/auth")
public class ScheduleController {
    @Autowired
    private IScheduleService scheduleService;

    @GetMapping(
        path = "/get-schedule/{veterinaryId}", 
        produces = MediaType.APPLICATION_JSON_VALUE
    )
	public ResponseEntity<List<Schedule>> findVeterinaryScheduleById(@PathVariable(name = "veterinaryId")Long veterinaryId) {
		return new ResponseEntity<>(scheduleService.getScheduleByVeterinaryId(veterinaryId),HttpStatus.OK);
	}
}
