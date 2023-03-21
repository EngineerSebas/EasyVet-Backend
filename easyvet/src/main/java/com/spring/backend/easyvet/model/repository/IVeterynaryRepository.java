package com.spring.backend.easyvet.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.backend.easyvet.dto.VeterinaryListDTO;
import com.spring.backend.easyvet.model.entity.Veterinary;
import com.spring.backend.easyvet.util.EVeterinaryStatus;

@Repository
public interface IVeterynaryRepository extends CrudRepository<Veterinary, Long>{

	@Query("SELECT new com.spring.backend.easyvet.dto.VeterinaryListDTO (id,dni,name, last_name, phone, country, city, email, bank_account, general_rate, priority_rate, type_bank, specialization_id, veterinary_status) FROM Veterinary veterinary WHERE veterinary.email = ?1")
	public VeterinaryListDTO findVeterinaryByEmail(String email);
	
	@Query("SELECT new com.spring.backend.easyvet.dto.VeterinaryListDTO (id,dni,name, last_name, phone, country, city, email, bank_account, general_rate, priority_rate, type_bank, specialization_id, veterinary_status) FROM Veterinary veterinary")
	List<VeterinaryListDTO> findVeterinaries();
	
	@Query("SELECT new com.spring.backend.easyvet.dto.VeterinaryListDTO (id,dni,name, last_name, phone, country, city, email, bank_account, general_rate, priority_rate, type_bank, specialization_id, veterinary_status) FROM Veterinary veterinary WHERE veterinary.veterinary_status = ?1")
	List<VeterinaryListDTO> findVeterinariesByStatus(EVeterinaryStatus status);

	public Optional<Veterinary> findByEmail(String email); 
	
	public Boolean existsByEmail(String email);
	
}
