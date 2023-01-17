package com.attornatus.projetopessoas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.attornatus.projetopessoas.model.Person;
import com.attornatus.projetopessoas.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Optional<Person> addNewPerson(Person person) {
//		if (personRepository.findByName(person.getName()).isPresent())
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pessoa já cadastrada", null);

		return Optional.of(personRepository.save(person));
	}

    public Optional<Person> editPerson(Person person) {
        Optional<Person> existingPerson = personRepository.findById(person.getId());
        
        if(!existingPerson.isPresent()) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada!", null);
        }
        
        return Optional.of(personRepository.save(person));
    }

}