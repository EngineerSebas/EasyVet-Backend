package com.spring.backend.easyvet.model.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.backend.easyvet.model.entity.Specialization;

@Repository
public interface ISpecializationRepository extends CrudRepository<Specialization, Long>{

	@Query("Select s from Specialization s WHERE s.id=?1")
	public Specialization findSpecializationById(Long id); 
	
}
