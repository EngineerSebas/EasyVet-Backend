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
 * @author Andrés.
 */

@Repository
public interface IPropietorRepository extends CrudRepository<Propietor, Long>{

	@Query("SELECT new com.spring.backend.easyvet.dto.PropietorListDTO (name, last_name, phone, country, city, email,dni) FROM Propietor propietor WHERE propietor.id = ?1")
	public PropietorListDTO findPropietorById(Long id);
	
	@Query("SELECT new com.spring.backend.easyvet.dto.PropietorListDTO (name, last_name, phone, country, city, email, dni) FROM Propietor propietor")
	public List<PropietorListDTO> findAllPropietors();
	
	public Optional<Propietor> findByEmail(String email); 
	
	public Boolean existsByEmail(String email);
	
}
