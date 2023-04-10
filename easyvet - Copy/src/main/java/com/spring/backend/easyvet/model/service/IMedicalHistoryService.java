package com.spring.backend.easyvet.model.service;

import java.util.List;

import com.spring.backend.easyvet.dto.MedicalHistoryDTO;
import com.spring.backend.easyvet.model.entity.MedicalHistory;

public interface IMedicalHistoryService {

	public void savePetMedicalHistory(Long petId, MedicalHistoryDTO petMedicalHistoryDTO);
	public void updatePetMedicalHistoryById(Long id, MedicalHistoryDTO petMedicalHistoryDTO);
	public List<MedicalHistory> getMedicalHistoryByPetId(Long petId);
	public void deleteMedicalHistoryById(long id);	
}
