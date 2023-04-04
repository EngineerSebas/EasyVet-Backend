package com.spring.backend.easyvet.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.backend.easyvet.model.entity.MedicalHistory;

@Repository
public interface IMedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {

}
