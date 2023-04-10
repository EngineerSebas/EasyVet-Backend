package com.spring.backend.easyvet.model.service.impl;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.backend.easyvet.dto.AppoinmentDTO;
import com.spring.backend.easyvet.exception.BadRequestException;
import com.spring.backend.easyvet.exception.ResourceNotFoundException;
import com.spring.backend.easyvet.model.entity.Appoinment;
import com.spring.backend.easyvet.model.entity.Propietor;
import com.spring.backend.easyvet.model.entity.Schedule;
import com.spring.backend.easyvet.model.entity.Veterinary;
import com.spring.backend.easyvet.model.repository.IAppoinmentRepository;
import com.spring.backend.easyvet.model.repository.IPropietorRepository;
import com.spring.backend.easyvet.model.repository.IScheduleRepository;
import com.spring.backend.easyvet.model.repository.IVeterynaryRepository;
import com.spring.backend.easyvet.model.service.IAppoinmentService;
import com.spring.backend.easyvet.util.EAppoinmentStatus;

@Service
public class AppoinmentServiceImpl implements IAppoinmentService{
	
	@Autowired
	private IAppoinmentRepository appoinmentRepository;
	
	@Autowired
	private IVeterynaryRepository veterynaryRepository;
	
	@Autowired
	private IPropietorRepository propietorRepository;

	@Autowired
	private IScheduleRepository scheduleRepository;


	@Override
	@Transactional
	public Appoinment registerAppoinment(Long propietorId, Long veterinaryId, AppoinmentDTO appoinmentDTO, LocalTime appoimentHour) {
		
		Propietor propietorOptional = propietorRepository.findById(propietorId)
        .orElseThrow(() -> new ResourceNotFoundException("Propietor with id " + propietorId + " was not found"));

		Veterinary veterinaryOptional = veterynaryRepository.findById(veterinaryId)
				.orElseThrow(() -> new ResourceNotFoundException("Veterinary with id " + veterinaryId + " was not found"));

		// cargar los horarios de un veterinario en la memoria
		List<Schedule> scheduleHours = scheduleRepository.findByVeterinaryId(veterinaryOptional.getId());

		// buscar la hora deseada en la lista de horarios
		Schedule schedule = scheduleHours.stream()
				.filter(s -> s.getHour().equals(appoimentHour))
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("The desired time is not in the schedule"));

		if (!schedule.isAvailable()) {
			System.out.println("El horario no está disponible");
			throw new IllegalStateException("The schedule is not available");
		}

		schedule.setAvailable(false);

		Appoinment appoinment = new Appoinment();
		appoinment.setCommentary(appoinmentDTO.getCommentary());
		appoinment.setDate_appoinment(appoinmentDTO.getDate_appoinment());
		appoinment.setRate_appoinment(appoinmentDTO.getRate_appoinment());
		appoinment.setType_appoinment(appoinmentDTO.getType_appoinment());
		appoinment.setVideocall_meet(appoinmentDTO.getVideocall_meet());
		appoinment.setName_veterynary(appoinmentDTO.getName_veterynary());
		appoinment.setName_propietor(appoinmentDTO.getName_propietor());
		// hora para la cita
		appoinment.setAppoinmentHour(appoimentHour);
		appoinment.setPropietor(propietorOptional);
		appoinment.setVeterynary(veterinaryOptional);
		appoinment.setAppoinment_status(EAppoinmentStatus.PENDING);

		appoinmentRepository.save(appoinment);
		return appoinment;
	
	}

	@Override
	@Transactional(readOnly = true)
	public Appoinment findAppoinmentById(Long id) {
		Optional<Appoinment> appoinment = appoinmentRepository.findById(id);
	    if (!appoinment.isPresent()) {
	        throw new ResourceNotFoundException("Appoinment with id " + id + " not found");
	    }
	    return appoinment.get();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Appoinment> findAllByPropietor_id(Long id) {
		List<Appoinment> appoinments = appoinmentRepository.findAllByPropietor_id(id);
		if (appoinments.isEmpty()) {
			throw new ResourceNotFoundException("Appoinment with id " + id + " not found");
		}
		return appoinments;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Appoinment> findAppoinmentByIdVeterynary(Long id) {
		List<Appoinment> appointments = appoinmentRepository.findByVeterynary_id(id);
		if (appointments.isEmpty()) {
			throw new ResourceNotFoundException("Appointments with veterinary id " + id + " not found");
		}
		return appointments;
	}



	@Override
	@Transactional(readOnly = true)
	public List<Appoinment> findAllAppoinments() {
	    return (List<Appoinment>) appoinmentRepository.findAll();
	}

	@Override
	@Transactional
	public Appoinment updateAppoinment(Long appoinmentId, Long propietorId, Long veterinaryId, AppoinmentDTO appoinmentDTO, LocalTime appoinmentHour) {

		Appoinment appoinment = appoinmentRepository.findById(appoinmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Appoinment not found with id " + appoinmentId));

		Propietor propietor = propietorRepository.findById(propietorId)
				.orElseThrow(() -> new ResourceNotFoundException("Propietor with id " + propietorId + " was not found"));

		Veterinary veterinary = veterynaryRepository.findById(veterinaryId)
				.orElseThrow(() -> new ResourceNotFoundException("Veterinary with id " + veterinaryId + " was not found"));

		appoinment.setCommentary(appoinmentDTO.getCommentary());
		appoinment.setDate_appoinment(appoinmentDTO.getDate_appoinment());
		appoinment.setRate_appoinment(appoinmentDTO.getRate_appoinment());
		appoinment.setType_appoinment(appoinmentDTO.getType_appoinment());
		appoinment.setVideocall_meet(appoinmentDTO.getVideocall_meet());
		appoinment.setPropietor(propietor);
		appoinment.setVeterynary(veterinary);
		appoinment.setAppoinment_status(appoinmentDTO.getAppoinment_status());

		// get the schedule of the veterinary
		List<Schedule> scheduleHours = scheduleRepository.findByVeterinaryId(veterinary.getId());

		// find the desired time
		Schedule desiredSchedule = scheduleHours.stream()
				.filter(s -> s.getHour().equals(appoinmentHour))
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("The desired time is not in the schedule"));

		// check if the desired time is available
		if (!desiredSchedule.isAvailable()) {
			throw new ResourceNotFoundException("The desired time is not available");
		}

		// check if the desired time is different from the current time
		if (!desiredSchedule.getHour().equals(appoinment.getAppoinmentHour())) {
			// release the old time
			Schedule oldSchedule = scheduleHours.stream()
					.filter(s -> s.getHour().equals(appoinment.getAppoinmentHour()))
					.findFirst()
					.orElseThrow(() -> new ResourceNotFoundException("The previous time is not in the schedule"));

			oldSchedule.setAvailable(true);
			desiredSchedule.setAvailable(false);
			appoinment.setAppoinmentHour(appoinmentHour);
		}

		return appoinmentRepository.save(appoinment);
	}

	@Override
	@Transactional
	public void deleteAppoinmentById(Long id) {
		Optional<Appoinment> appoinmentOptional = appoinmentRepository.findById(id);
        if(appoinmentOptional.isPresent()) {
        	appoinmentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Appoinment not found with id " + id);
        }
	}

	@Override
	@Transactional
	public void confirmAppointment(Long veterinaryId, Long appointmentId, Boolean confirmed) {
		Appoinment appointment = appoinmentRepository.findById(appointmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Appointment with id" + appointmentId + "not found"));

		if (!appointment.getVeterynary().getId().equals(veterinaryId)) {
			throw new BadRequestException("This appointment doesn't belong to the provided veterinary.");
		}
		if(confirmed){
			appointment.setAppoinment_status(EAppoinmentStatus.CONFIRMED);
		}else {
			appointment.setAppoinment_status(EAppoinmentStatus.NOT_CONFIRMED);
		}
		appoinmentRepository.save(appointment);

	}


}
