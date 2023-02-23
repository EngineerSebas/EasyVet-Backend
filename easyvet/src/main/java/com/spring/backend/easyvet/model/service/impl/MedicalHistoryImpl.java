package com.spring.backend.easyvet.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void savePetMedicalHistory(MedicalHistoryDTO petMedicalHistoryDTO) {
        MedicalHistory petMedicalHistory = new MedicalHistory();
        BeanUtils.copyProperties(petMedicalHistoryDTO, petMedicalHistory, "id", "petId");
        Pet pet = petRepository.findPetById(petMedicalHistoryDTO.getPetId());
        petMedicalHistory.setPet_id(pet.getId());
        petMedicalHistoryRepository.save(petMedicalHistory);
    }

    public void updatePetMedicalHistoryById(Long id, MedicalHistoryDTO petMedicalHistoryDTO) {

        Optional<MedicalHistory> petMedicalHistoryOptional = petMedicalHistoryRepository.findById(id);
        if (!petMedicalHistoryOptional.isPresent()) {
            throw new ResourceNotFoundException("PetMedicalHistory with id " + id + " not found");
        }

        MedicalHistory petMedicalHistory = petMedicalHistoryOptional.get();

        BeanUtils.copyProperties(petMedicalHistoryDTO, petMedicalHistory, "id", "petId");

        Optional<Pet> petOptional = petRepository.findById(petMedicalHistoryDTO.getPetId());
        if (!petOptional.isPresent()) {
            throw new ResourceNotFoundException("Pet with id " + petMedicalHistoryDTO.getPetId() + " not found");
        }
        Pet pet = petOptional.get();
        petMedicalHistory.setPet(pet);

        petMedicalHistoryRepository.save(petMedicalHistory);
    }

    /*
     * @Override
     * public List<MedicalHistory> getMedicalHistoryByPetId(Long petId) {
     * Optional<Pet> petOptional = petRepository.findById(petId);
     * if (!petOptional.isPresent()) {
     * throw new ResourceNotFoundException("Pet with id " + petId + " not found");
     * }
     * 
     * Optional<MedicalHistory> medicalHistory =
     * petMedicalHistoryRepository.findById(petId);
     * if(!medicalHistory.isPresent()) {
     * throw new ResourceNotFoundException("A medical History for pet " + petId +
     * " not found");
     * }
     * 
     * 
     * MedicalHistory medicalHistoryResult = medicalHistory.get();
     * System.out.println("medical" + medicalHistoryResult.toString());
     * return (List<MedicalHistory>) medicalHistoryResult;
     * }
     */

    @Override
    public List<MedicalHistory> getMedicalHistoryByPetId(Long petId) {

        Optional<Pet> petOptional = petRepository.findById(petId);
        if (!petOptional.isPresent()) {
            throw new ResourceNotFoundException("Pet with id " + petId + " not found");
        }

        Optional<MedicalHistory> medicalHistory = petMedicalHistoryRepository.findById(petId);
        if (!medicalHistory.isPresent() || medicalHistory.isEmpty()) {
            throw new ResourceNotFoundException("A medical History for pet " + petId + " not found");
        }

        List<MedicalHistory> medicalHistoryList = new ArrayList<>();
        medicalHistoryList.add(medicalHistory.get());

        return medicalHistoryList;

    }

}
