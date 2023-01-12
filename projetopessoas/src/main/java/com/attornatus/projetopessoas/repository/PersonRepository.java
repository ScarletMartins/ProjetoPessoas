package com.attornatus.projetopessoas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.attornatus.projetopessoas.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	public List<Person> findAllByNameContainingIgnoreCase(@Param("name") String name);
	
	public Optional<Person> findByName(String name);
}
