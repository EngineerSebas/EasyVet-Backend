package com.spring.backend.easyvet.model.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.backend.easyvet.dto.MedicalHistoryDTO;
import com.spring.backend.easyvet.exception.ResourceNotFoundException;
import com.spring.backend.easyvet.model.entity.MedicalHistory;
import com.spring.backend.easyvet.model.entity.Pet;
import com.spring.backend.easyvet.model.repository.IMedicalHistoryRepository;
import com.spring.backend.easyvet.model.repository.IPetRepository;
import com.spring.backend.easyvet.model.service.IMedicalHistoryService;

@Service
public class MedicalHistoryImpl implements IMedicalHistoryService {

    @Autowired
    private IMedicalHistoryRepository petMedicalHistoryRepository;

    @Autowired
    private IPetRepository petRepository;

    @Override
    public void savePetMedicalHistory(Long petId, MedicalHistoryDTO petMedicalHistoryDTO) {
        Pet pet = petRepository.findById(petId)
            .orElseThrow(() -> new ResourceNotFoundException("Pet with id " + petId + " not found"));

        MedicalHistory petMedicalHistory = new MedicalHistory();
        BeanUtils.copyProperties(petMedicalHistoryDTO, petMedicalHistory);
        petMedicalHistory.setPet_id(pet.getId());

        petMedicalHistoryRepository.save(petMedicalHistory);
    }


    @Override
    public void updatePetMedicalHistoryById(Long id, MedicalHistoryDTO petMedicalHistoryDTO) {
        MedicalHistory petMedicalHistory = petMedicalHistoryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Pet Medical History with id " + id + " not found"));

        Pet pet = petRepository.findById(petMedicalHistory.getPet_id())
            .orElseThrow(() -> new ResourceNotFoundException("Pet with id " + petMedicalHistory.getPet_id() + " not found"));
    
        BeanUtils.copyProperties(petMedicalHistoryDTO, petMedicalHistory, "id", "petId");
        petMedicalHistory.setPet(pet);
    
        petMedicalHistoryRepository.save(petMedicalHistory);
    }

    @Override
    public List<MedicalHistory> getMedicalHistoryByPetId(Long petId) {
        if (!petRepository.existsById(petId)) {
            throw new ResourceNotFoundException("Pet with id " + petId + " not found");
        }

        return petMedicalHistoryRepository.findMedicalHistoryByPetId(petId)
            .map(Collections::singletonList)
            .orElseThrow(() -> new ResourceNotFoundException("A medical History for pet " + petId + " not found"));
    }

    @Override
    public void deleteMedicalHistoryById(long id) {
        if (!petMedicalHistoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Medical History with id " + id + " not found");
        }

        petMedicalHistoryRepository.deleteById(id);
    }


}
