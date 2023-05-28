package com.spring.backend.easyvet.model.service.impl;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityNotFoundException;

import com.spring.backend.easyvet.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.spring.backend.easyvet.exception.ResourceNotFoundException;
import com.spring.backend.easyvet.model.entity.Role;
import com.spring.backend.easyvet.model.entity.Schedule;
import com.spring.backend.easyvet.model.entity.Specialization;
import com.spring.backend.easyvet.model.entity.Veterinary;
import com.spring.backend.easyvet.model.repository.IRoleRepository;
import com.spring.backend.easyvet.model.repository.IScheduleRepository;
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
	private IScheduleRepository scheduleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional(readOnly = true)
	public VeterinaryListDTO findVeterinaryByEmail(String email) {
		VeterinaryListDTO veterinaryListDTO = veterynaryRepository.findVeterinaryByEmail(email);
        if(veterinaryListDTO == null){
            throw new EntityNotFoundException("the veterinary with id " + email + " was not found");
        }
        return veterinaryListDTO; 
	}

	@Transactional(readOnly = true)
	public List<VeterinaryListDTO> findAllVeterinaries() {
	    return veterynaryRepository.findVeterinaries();
	}
	
	@Override
	public void registerVeterinary(VeterinaryDTO veterinary, LocalTime start, LocalTime end, boolean isAvailable) {
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

			Specialization specialization = specializationService.findSpecializationById(veterinary.getSpecialization_id());
			user.setSpecialization_id(specialization.getId());

			user.setDni(veterinary.getDni());

			// Create schedule for veterinary
			for (LocalTime hour = start; hour.compareTo(end) <= 0; hour = hour.plusHours(1)) {
				Schedule schedule = new Schedule();
				schedule.setHour(hour);
				schedule.setAvailable(isAvailable);
				schedule.setVeterinary(user);
				user.getSchedule().add(schedule);
			}

			veterynaryRepository.save(user);

		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Required properties are missing");
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
	@Transactional
	public void updateUserProfileImageByEmail(String email, VeterinaryImgProfileDTO userProfileImageDTO) {
		Veterinary user = veterynaryRepository.findByEmail(email).orElseThrow(() ->
				new ResponseStatusException(HttpStatus.NOT_FOUND, "Veterinary not found"));
		try {
			user.setImg_profile(userProfileImageDTO.getImg_profile());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
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

	@Override
	public void updateVeterinaryScheduleById(Long veterinaryId, LocalTime start, LocalTime end, boolean isAvailable) {
		if (start.compareTo(end) >= 0) {
			throw new IllegalArgumentException("La hora de inicio debe ser menor que la hora de finalización.");
		}

		Veterinary veterinary = veterynaryRepository.findById(veterinaryId)
				.orElseThrow(() -> new ResourceNotFoundException("Veterinary with id " + veterinaryId + " was not found"));

		List<Schedule> existingSchedule = scheduleRepository.findByVeterinaryId(veterinaryId);
		if (!existingSchedule.isEmpty()) {
			scheduleRepository.deleteAll(existingSchedule);
		}

		// Intervalo de tiempo, por ejemplo, 1 hora
		Duration timeInterval = Duration.ofHours(1);

		long numberOfIntervals = Duration.between(start, end).dividedBy(timeInterval) + 1;

		List<Schedule> newSchedules = Stream.iterate(start, hour -> hour.plus(timeInterval))
				.limit(numberOfIntervals)
				.map(hour -> {
					Schedule schedule = new Schedule();
					schedule.setHour(hour);
					schedule.setAvailable(isAvailable);
					schedule.setVeterinary(veterinary);
					return schedule;
				})
				.collect(Collectors.toList());

		scheduleRepository.saveAll(newSchedules);
	}



}
