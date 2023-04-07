package com.spring.backend.easyvet.model.service;

import java.time.LocalTime;
import java.util.List;

import com.spring.backend.easyvet.dto.AppoinmentDTO;
import com.spring.backend.easyvet.model.entity.Appoinment;

public interface IAppoinmentService {
	
	public Appoinment registerAppoinment(Long propietorId, Long veterinaryId, AppoinmentDTO appoinmentDTO, LocalTime appoimentHour);

	public Appoinment findAppoinmentById(Long id);

	public List<Appoinment> findAllByPropietor_id(Long id);

	public Appoinment findAppoinmentByIdVeterynary(Long id);
	
	public List<Appoinment> findAllAppoinments();
	
	public Appoinment updateAppoinment(Long appoinmentId, Long propietorId, Long veterinaryId, AppoinmentDTO appoinmentDTO, LocalTime appoimentHour);
	
	public void deleteAppoinmentById(Long id);
	
	public void confirmAppointment(Long veterinaryId, Long appointmentId);
}
