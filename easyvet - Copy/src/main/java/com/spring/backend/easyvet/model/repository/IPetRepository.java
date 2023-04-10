package com.spring.backend.easyvet.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.backend.easyvet.model.entity.Pet;

/**
 * IPet Repository.
 *
 * @author Sebastian.
 */

@Repository
public interface IPetRepository extends CrudRepository<Pet, Long>{

	public Pet findPetById(Long id);
	
	@Query("SELECT p from Pet p WHERE p.propietor.dni =?1")
	public List<Pet> findPetsByDNI(String dni);
	
}
