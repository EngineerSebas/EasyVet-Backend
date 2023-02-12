package com.spring.backend.easyvet.model.service;

import java.util.List;

import com.spring.backend.easyvet.dto.PropietorListDTO;
import com.spring.backend.easyvet.dto.PropietorUpdateDTO;
import com.spring.backend.easyvet.model.entity.Propietor;

public interface IPropietorService {

	//public Propietor findPropietorById(Long id);
	
	public List<PropietorListDTO> findAllPropietors();

	public PropietorListDTO findPropietorById(Long id);
	
    public void registerPropietor(Propietor propietor);
    public void updatePropietor(String email, PropietorUpdateDTO propietorUpdateDTO, String currentPassword);
	
}
