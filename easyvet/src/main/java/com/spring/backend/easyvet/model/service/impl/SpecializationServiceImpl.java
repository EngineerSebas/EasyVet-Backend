package com.spring.backend.easyvet.model.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.backend.easyvet.dto.SpecializationDTO;
import com.spring.backend.easyvet.model.entity.Specialization;
import com.spring.backend.easyvet.model.repository.ISpecializationRepository;
import com.spring.backend.easyvet.model.service.ISpecializationService;

@Service
public class SpecializationServiceImpl implements ISpecializationService{

	@Autowired
	private ISpecializationRepository specializationRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Specialization findSpecializationById(Long id) {
		Specialization specialization = specializationRepository.findSpecializationById(id);
		
		if(specialization == null) {
			throw new EntityNotFoundException("the specialization with id " + id + " is not found");
		}
		
		return specialization;
		
	}

	@Override
	@Transactional
	public Specialization createSpecialization(SpecializationDTO specializationDTO) {
		Specialization specialization = new Specialization();
		BeanUtils.copyProperties(specializationDTO, specialization);
		return specializationRepository.save(specialization);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Specialization> findAllSpecializations() {
		return (List<Specialization>) specializationRepository.findAll();
	}

	@Override
	@Transactional
	public Specialization updateSpecializationById(Long id, SpecializationDTO specializationDTO) {
		
		Specialization specialization = specializationRepository.findSpecializationById(id);
		
		specialization.setPet_specialization((specializationDTO.getPet_specialization()));
		
		return specializationRepository.save(specialization);
	}

	@Override
	@Transactional
	public void deleteSpecializationById(Long id) {
		this.findSpecializationById(id);
		specializationRepository.deleteById(id);
	}

}
