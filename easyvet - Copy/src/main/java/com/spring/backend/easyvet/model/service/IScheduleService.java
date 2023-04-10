package com.spring.backend.easyvet.model.service;

import java.util.List;

import com.spring.backend.easyvet.model.entity.Schedule;

public interface IScheduleService {
    public List<Schedule> getScheduleByVeterinaryId(Long veterinaryId);
}
