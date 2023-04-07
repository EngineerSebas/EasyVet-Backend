package com.spring.backend.easyvet.model.service.impl;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.backend.easyvet.exception.ResourceNotFoundException;
import com.spring.backend.easyvet.model.entity.Schedule;
import com.spring.backend.easyvet.model.repository.IScheduleRepository;
import com.spring.backend.easyvet.model.repository.IVeterynaryRepository;
import com.spring.backend.easyvet.model.service.IScheduleService;

@Service
public class ScheduleService implements IScheduleService {
    
    @Autowired
    private IScheduleRepository scheduleRepository;

    @Autowired
    private IVeterynaryRepository veterynaryRepository;

    @Override
    public List<Schedule> getScheduleByVeterinaryId(Long veterinaryId) {
        if(!veterynaryRepository.existsById(veterinaryId)){
            new ResourceNotFoundException("Veterinary with id " + veterinaryId + " not found");
        }

        return this.scheduleRepository.findByVeterinaryId(veterinaryId);
    }

    
}
