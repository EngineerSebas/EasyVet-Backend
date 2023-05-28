package com.spring.backend.easyvet.model.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.spring.backend.easyvet.dto.*;
import com.spring.backend.easyvet.util.EVeterinaryStatus;

public interface IVeterynaryService {
	
	public List<VeterinaryListDTO> findAllVeterinaries();

	public VeterinaryListDTO findVeterinaryByEmail(String email);
	
	public List<VeterinaryListDTO> findVeterinariesByStatus(EVeterinaryStatus status);
	
	public void updateVeterinary(String email, VeterinaryUpdateDTO veterinaryUpdateDTO, String currentPassword);
	
	public void updateVeterinaryStatusById(String email, VeterinaryStatusDTO veterinaryStatusDTO);

	//public void registerVeterinary(VeterinaryDTO veterinary);
	public void updateUserProfileImageByEmail(String email, VeterinaryImgProfileDTO userProfileImageDTO);
	public void registerVeterinary(VeterinaryDTO veterinary, LocalTime start, LocalTime end, boolean isAvailable);

	public void updateVeterinaryScheduleById(Long veterinaryId, LocalTime start, LocalTime end, boolean isAvailable);
	
}
