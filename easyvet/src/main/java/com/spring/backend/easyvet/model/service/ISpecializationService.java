package com.spring.backend.easyvet.model.service;

import java.util.List;

import com.spring.backend.easyvet.dto.SpecializationDTO;
import com.spring.backend.easyvet.model.entity.Specialization;

public interface ISpecializationService {

	public List<Specialization> findAllSpecializations();
	public Specialization updateSpecializationById(Long id, SpecializationDTO specializationDTO);
	public Specialization findSpecializationById(Long id);
	public Specialization createSpecialization(SpecializationDTO specializationDTO);
	public void deleteSpecializationById(Long id);
}
