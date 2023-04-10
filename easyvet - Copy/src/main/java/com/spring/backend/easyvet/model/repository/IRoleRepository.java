package com.spring.backend.easyvet.model.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.backend.easyvet.model.entity.Role;

@Repository
public interface IRoleRepository extends CrudRepository<Role, Long>{

	public Optional<Role> findByName(String name); 
	
}
