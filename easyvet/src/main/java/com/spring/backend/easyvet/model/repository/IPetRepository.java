package com.spring.backend.easyvet.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.backend.easyvet.model.entity.Pet;

/**
 * IPet Repository.
 * 
 * @author Andrés.
 */

@Repository
public interface IPetRepository extends CrudRepository<Pet, Long>{

	public Pet findPetById(Long id);
	
}
