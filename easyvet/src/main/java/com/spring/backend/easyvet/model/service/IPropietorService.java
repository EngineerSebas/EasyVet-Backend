package com.spring.backend.easyvet.model.service;

import com.spring.backend.easyvet.model.entity.Propietor;

public interface IPropietorService {

	public Propietor findPropietorById(Long id);
	//ResponseEntity<String> login(Propietor propietor);
    public void registerPropietor(Propietor propietor);
	
}
