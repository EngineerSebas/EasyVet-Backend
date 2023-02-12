package com.spring.backend.easyvet.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.backend.easyvet.model.entity.Appoinment;

@Repository
public interface IAppoinmentRepository extends CrudRepository<Appoinment, Long>{

}
