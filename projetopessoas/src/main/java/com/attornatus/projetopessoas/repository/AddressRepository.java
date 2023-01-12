package com.attornatus.projetopessoas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.attornatus.projetopessoas.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
	
	public Optional<Address> findByCity(String city);
	
	public List<Address> findAllByPersonId(@Param("id") Long id);

	public List<Address> findByMainAddress(boolean mainAddress);

}
