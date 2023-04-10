package com.spring.backend.easyvet.model.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.backend.easyvet.dto.PetDTO;
import com.spring.backend.easyvet.model.entity.Pet;
import com.spring.backend.easyvet.model.entity.Propietor;
import com.spring.backend.easyvet.model.repository.IPetRepository;
import com.spring.backend.easyvet.model.repository.IPropietorRepository;
import com.spring.backend.easyvet.model.service.IPetService;

import lombok.RequiredArgsConstructor;

/**
 * Pet ServiceImpl.
 * 
 * @author Andrés.
 */

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements IPetService{
	
	@Autowired
	private IPetRepository petRepository;
	
	@Autowired
	private IPropietorRepository propietorRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Pet> findAllPets() {
		return (List<Pet>) petRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Pet findPetById(Long id) {
		Pet pet = petRepository.findPetById(id);
		if(pet == null) {
			throw new EntityNotFoundException("The pet with id " + id + " is not found");
		}
		return pet;
	}

	@Override
	@Transactional
	public Pet createPet(PetDTO petDTO) {
		Pet pet = new Pet();
		
		Optional<Propietor> propietorOptional = propietorRepository.findById(petDTO.getPropietor_id());
		if(propietorOptional.isPresent()) {
			Propietor propietor = propietorOptional.get();
			pet.setPropietor(propietor);
		}
		BeanUtils.copyProperties(petDTO, pet);
		return petRepository.save(pet);
	}

	@Override
	@Transactional
	public Pet updatePet(Long id, PetDTO petDTO) {
		Pet pet = petRepository.findPetById(id);
		
		pet.setName(petDTO.getName());
		pet.setAge(petDTO.getAge());
		pet.setBreed(petDTO.getBreed());
		pet.setSex(petDTO.getSex());
		pet.setType(petDTO.getType());
		
		return petRepository.save(pet);
	}

	@Override
	@Transactional
	public void deletePetById(Long id) {
		this.findPetById(id);
		petRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pet> findPetsByDNI(String dni) {
		List<Pet> pets = petRepository.findPetsByDNI(dni);
		if(pets == null || pets.isEmpty() ) {
			throw new EntityNotFoundException("The asociated dni " + dni + " was not found");
		}
		return pets;
		
	}
}
