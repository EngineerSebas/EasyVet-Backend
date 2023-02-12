package com.spring.backend.easyvet.model.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.spring.backend.easyvet.dto.VeterinaryDTO;
import com.spring.backend.easyvet.dto.VeterinaryListDTO;
import com.spring.backend.easyvet.dto.VeterinaryStatusDTO;
import com.spring.backend.easyvet.dto.VeterinaryUpdateDTO;
import com.spring.backend.easyvet.model.entity.Role;
import com.spring.backend.easyvet.model.entity.Specialization;
import com.spring.backend.easyvet.model.entity.Veterinary;
import com.spring.backend.easyvet.model.repository.IRoleRepository;
import com.spring.backend.easyvet.model.repository.IVeterynaryRepository;
import com.spring.backend.easyvet.model.service.ISpecializationService;
import com.spring.backend.easyvet.model.service.IVeterynaryService;
import com.spring.backend.easyvet.util.EVeterinaryStatus;

@Service
public class VeterinaryServiceImpl implements IVeterynaryService {
	
	@Autowired
	private IVeterynaryRepository veterynaryRepository;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private ISpecializationService specializationService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional(readOnly = true)
	public VeterinaryListDTO findVeterinaryById(Long id) {
		VeterinaryListDTO veterinaryListDTO = veterynaryRepository.findVeterinaryById(id);
        if(veterinaryListDTO == null){
            throw new EntityNotFoundException("the veterinary with id " + id + " was not found");
        }
        return veterinaryListDTO; 
	}

	@Transactional(readOnly = true)
	public List<VeterinaryListDTO> findAllVeterinaries() {
	    return veterynaryRepository.findVeterinaries();
	}

	@Override
	public void registerVeterinary(VeterinaryDTO veterinary) {
		
		Veterinary user = new Veterinary();
		
		try {
			user.setCity(veterinary.getCity());
			user.setCountry(veterinary.getCountry());
			user.setEmail(veterinary.getEmail());
			user.setLast_name(veterinary.getLast_name());
			user.setName(veterinary.getName());
			user.setPassword(passwordEncoder.encode(veterinary.getPassword()));
			user.setPhone(veterinary.getPhone());
			
			Role role = roleRepository.findByName("ROLE_VETERYNARY").get();
			user.setRole(role);
			
			user.setBank_account(veterinary.getBank_account());
			user.setGeneral_rate(veterinary.getGeneral_rate());
			user.setPriority_rate(veterinary.getPriority_rate());
			user.setType_bank(veterinary.getType_bank());
			
			veterinary.setVeterinary_status(EVeterinaryStatus.DISPONIBLE);
			
			user.setVeterinary_status(veterinary.getVeterinary_status());
			
			Specialization specialization = specializationService.
					findSpecializationById(veterinary.getSpecialization_id());
			
			user.setSpecialization_id(specialization.getId());
			
			user.setDni(veterinary.getDni());
			
			veterynaryRepository.save(user);
			
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST, 
					 "Required properties are missing");
		}
	}

	@Override
	@Transactional
	public void updateVeterinary(String email, VeterinaryUpdateDTO veterinaryUpdateDTO, String currentPassword) {
		
		Veterinary user = veterynaryRepository.findByEmail(veterinaryUpdateDTO.getEmail()).get();
		
		try {
			if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
				throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Incorrect password");
			}
			
			user.setCity(veterinaryUpdateDTO.getCity());
			user.setCountry(veterinaryUpdateDTO.getCountry());
			user.setEmail(veterinaryUpdateDTO.getEmail());
			user.setLast_name(veterinaryUpdateDTO.getLast_name());
			user.setName(veterinaryUpdateDTO.getName());
			user.setPassword(passwordEncoder.encode(veterinaryUpdateDTO.getPassword()));
			user.setPhone(veterinaryUpdateDTO.getPhone());
			
			user.setBank_account(veterinaryUpdateDTO.getBank_account());
			user.setGeneral_rate(veterinaryUpdateDTO.getGeneral_rate());
			user.setPriority_rate(veterinaryUpdateDTO.getPriority_rate());
			user.setType_bank(veterinaryUpdateDTO.getType_bank());
			user.setVeterinary_status(veterinaryUpdateDTO.getVeterinary_status());
			
			Specialization specialization = specializationService.
					findSpecializationById(veterinaryUpdateDTO.getSpecialization_id());
			
			user.setSpecialization_id(specialization.getId());
			
			user.setDni(veterinaryUpdateDTO.getDni());
			
			veterynaryRepository.save(user);
			
		} catch (Exception e) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST, 
					 "Required properties are missing");
		}
	
	}

	@Override
	@Transactional
	public void updateVeterinaryStatusById(String email, VeterinaryStatusDTO veterinaryStatusDTO) {
		Veterinary user = veterynaryRepository.findByEmail(email).get();
		
		try {
			user.setVeterinary_status(veterinaryStatusDTO.getVeterinary_status());
		} catch (Exception e) {
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST, 
					 "Required properties are missing");
		}
	}

	@Override
	public List<VeterinaryListDTO> findVeterinariesByStatus(EVeterinaryStatus status) {
		List<VeterinaryListDTO> veterinaryListDTO = veterynaryRepository.findVeterinariesByStatus(status);
        if(veterinaryListDTO == null){
            throw new EntityNotFoundException("the veterinary with status " + status + " was not found");
        }
        return veterinaryListDTO; 
	}

}
