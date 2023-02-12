package com.spring.backend.easyvet.model.service;

import java.util.List;

import com.spring.backend.easyvet.dto.AppoinmentDTO;
import com.spring.backend.easyvet.model.entity.Appoinment;

public interface IAppoinmentService {
	
	public Appoinment registerAppoinment(AppoinmentDTO appoinmentDTO);
	
	public Appoinment findAppoinmentById(Long id);
	
	public List<Appoinment> findAllAppoinments();
	
	public Appoinment updateAppoinment(Long id, AppoinmentDTO appoinmentDTO);
	
	public void deleteAppoinmentById(Long id);
}
