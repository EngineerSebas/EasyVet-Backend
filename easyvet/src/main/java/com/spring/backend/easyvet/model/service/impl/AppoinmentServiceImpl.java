package com.spring.backend.easyvet.model.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.backend.easyvet.dto.AppoinmentDTO;
import com.spring.backend.easyvet.exception.BadRequestException;
import com.spring.backend.easyvet.exception.ResourceNotFoundException;
import com.spring.backend.easyvet.model.entity.Appoinment;
import com.spring.backend.easyvet.model.entity.Propietor;
import com.spring.backend.easyvet.model.entity.Veterinary;
import com.spring.backend.easyvet.model.repository.IAppoinmentRepository;
import com.spring.backend.easyvet.model.repository.IPropietorRepository;
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

	@Override
	@Transactional
	public Appoinment registerAppoinment(AppoinmentDTO appoinmentDTO) {
		
		Appoinment appoinment = new Appoinment();
		appoinment.setCommentary(appoinmentDTO.getCommentary());
		appoinment.setDate_appoinment(appoinmentDTO.getDate_appoinment());
		appoinment.setRate_appoinment(appoinmentDTO.getRate_appoinment());
		appoinment.setType_appoinment(appoinmentDTO.getType_appoinment());
		appoinment.setVideocall_meet(appoinmentDTO.getVideocall_meet());
		appoinment.setName_veterynary(appoinmentDTO.getName_veterynary());

		Optional<Propietor> propietorOptional = propietorRepository.findById(appoinmentDTO.getPropietor_id());
		if(propietorOptional.isPresent()) {
			Propietor propietor = propietorOptional.get();
			appoinment.setPropietor(propietor);
		}
		
		Optional<Veterinary> veterinaryOptional = veterynaryRepository.findById(appoinmentDTO.getVeterynary_id());
		
		if(veterinaryOptional.isPresent()) {
			Veterinary veterinary = veterinaryOptional.get();
			appoinment.setVeterynary(veterinary);
		}
		
		appoinment.setAppoinment_status(EAppoinmentStatus.NOT_CONFIRMED);
		
		BeanUtils.copyProperties(appoinmentDTO, appoinment);
		return appoinmentRepository.save(appoinment);	
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
		List<Appoinment> appoinments = appoinmentRepository.findAllByVeterynary_id(id);
		if (appoinments.isEmpty()) {
			throw new ResourceNotFoundException("Appoinment with id " + id + " not found");
		}
		return appoinments;
	}


	@Override
	@Transactional(readOnly = true)
	public List<Appoinment> findAllAppoinments() {
	    return (List<Appoinment>) appoinmentRepository.findAll();
	}

	@Override
	@Transactional
    public Appoinment updateAppoinment(Long appoinmentId, AppoinmentDTO appoinmentDTO) {
		
        Optional<Appoinment> appoinmentOptional = appoinmentRepository.findById(appoinmentId);
        
        if(appoinmentOptional.isPresent()) {
        	
            Appoinment appoinment = appoinmentOptional.get();
            appoinment.setCommentary(appoinmentDTO.getCommentary());
            appoinment.setDate_appoinment(appoinmentDTO.getDate_appoinment());
            appoinment.setRate_appoinment(appoinmentDTO.getRate_appoinment());
            appoinment.setType_appoinment(appoinmentDTO.getType_appoinment());
            appoinment.setVideocall_meet(appoinmentDTO.getVideocall_meet());
            
            Optional<Propietor> propietorOptional = propietorRepository.findById(appoinmentDTO.getPropietor_id());
    		if(propietorOptional.isPresent()) {
    			Propietor propietor = propietorOptional.get();
    			appoinment.setPropietor(propietor);
    		}
            
            Optional<Veterinary> veterinaryOptional = veterynaryRepository.findById(appoinmentDTO.getVeterynary_id());
            if(veterinaryOptional.isPresent()) {
                Veterinary veterinary = veterinaryOptional.get();
                appoinment.setVeterynary(veterinary);
            }
            appoinment.setAppoinment_status(appoinmentDTO.getAppoinment_status());
            
            BeanUtils.copyProperties(appoinmentDTO, appoinment);
            return appoinmentRepository.save(appoinment);	
        } else {
            throw new ResourceNotFoundException("Appoinment not found with id " + appoinmentId);
        }
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
