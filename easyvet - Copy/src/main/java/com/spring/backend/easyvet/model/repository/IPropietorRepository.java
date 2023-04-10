package com.spring.backend.easyvet.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.backend.easyvet.dto.PropietorListDTO;
import com.spring.backend.easyvet.model.entity.Propietor;

/**
 * IPropietor Repository.
 *
 * @author Sebastian.
 */

@Repository
public interface IPropietorRepository extends CrudRepository<Propietor, Long>{

	@Query("SELECT new com.spring.backend.easyvet.dto.PropietorListDTO (id,name, last_name, phone, country, city, email,dni) FROM Propietor propietor WHERE propietor.email = ?1")
	public PropietorListDTO findPropietorByEmail(String email);
	
	@Query("SELECT new com.spring.backend.easyvet.dto.PropietorListDTO (id,name, last_name, phone, country, city, email, dni) FROM Propietor propietor")
	public List<PropietorListDTO> findAllPropietors();
	
	public Optional<Propietor> findByEmail(String email); 
	
	public Boolean existsByEmail(String email);
	
}
