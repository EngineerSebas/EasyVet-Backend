package com.spring.backend.easyvet.model.service;

import java.util.List;

import com.spring.backend.easyvet.dto.VeterinaryDTO;
import com.spring.backend.easyvet.dto.VeterinaryListDTO;
import com.spring.backend.easyvet.dto.VeterinaryStatusDTO;
import com.spring.backend.easyvet.dto.VeterinaryUpdateDTO;
import com.spring.backend.easyvet.util.EVeterinaryStatus;

public interface IVeterynaryService {
	
	public List<VeterinaryListDTO> findAllVeterinaries();

	public VeterinaryListDTO findVeterinaryById(Long id);
	
	public List<VeterinaryListDTO> findVeterinariesByStatus(EVeterinaryStatus status);
	
	public void updateVeterinary(String email, VeterinaryUpdateDTO veterinaryUpdateDTO, String currentPassword);
	
	public void updateVeterinaryStatusById(String email, VeterinaryStatusDTO veterinaryStatusDTO);

	public void registerVeterinary(VeterinaryDTO veterinary);
	
}
