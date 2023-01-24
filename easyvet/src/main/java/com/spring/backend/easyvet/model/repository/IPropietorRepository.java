package com.spring.backend.easyvet.model.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.backend.easyvet.model.entity.Propietor;

/**
 * IPropietor Repository.
 * 
 * @author Andrés.
 */

@Repository
public interface IPropietorRepository extends CrudRepository<Propietor, Long>{

	public Propietor findPropietorById(Long id);
	
	public Optional<Propietor> findByEmail(String email); 
	
	public Boolean existsByEmail(String email);
	
}
