package com.spring.backend.easyvet.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.backend.easyvet.model.entity.Appoinment;

import java.util.List;

@Repository
public interface IAppoinmentRepository extends CrudRepository<Appoinment, Long>{
    public List<Appoinment> findAllByPropietor_id(Long id);
    public List<Appoinment>  findByVeterynary_id(Long id);

    @Query("SELECT a, p.name, p.email, p.img_profile FROM Appoinment a JOIN a.propietor p")
    List<Object[]> findAllWithPropietorAndVeterynary();
}
