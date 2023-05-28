package com.spring.backend.easyvet.model.service;

import java.util.List;

import com.spring.backend.easyvet.dto.PetDTO;
import com.spring.backend.easyvet.dto.PetImgProfileDTO;
import com.spring.backend.easyvet.dto.PropietorImgProfileDTO;
import com.spring.backend.easyvet.model.entity.Pet;

/**
 * IPet Service.
 *
 * @author Sebastian.
 */

public interface IPetService {

	public List<Pet> findAllPets();

	public Pet findPetById(Long id);
	
	public List<Pet> findPetsByDNI(String dni);

	public Pet createPet(PetDTO petDTO);

	public Pet updatePet(Long id, PetDTO petDTO);

	public void deletePetById(Long id);

	public void updatePetProfileImageById(Long id, PetImgProfileDTO petImgProfileDTO);


}
