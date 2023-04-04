package com.spring.backend.easyvet.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.backend.easyvet.model.entity.Appoinment;

import java.util.List;

@Repository
public interface IAppoinmentRepository extends CrudRepository<Appoinment, Long>{

    public List<Appoinment> findAllByPropietor_id(Long id);


    public List<Appoinment> findAllByVeterynary_id(Long id);
}
