package com.spring.backend.easyvet.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.backend.easyvet.model.entity.MedicalHistory;

@Repository
public interface IMedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
    
    @Query("SELECT mh FROM MedicalHistory mh WHERE mh.pet_id = :petId")
    public Optional<MedicalHistory> findMedicalHistoryByPetId(@Param("petId") Long petId);
}
