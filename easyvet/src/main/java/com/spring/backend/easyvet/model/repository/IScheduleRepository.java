package com.spring.backend.easyvet.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.backend.easyvet.model.entity.Schedule;

public interface IScheduleRepository extends JpaRepository<Schedule, Long> {
    public List<Schedule> findByVeterinaryId(Long veterinaryId);
}
