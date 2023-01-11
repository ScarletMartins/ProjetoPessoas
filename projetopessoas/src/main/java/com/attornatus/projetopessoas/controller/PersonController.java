package com.attornatus.projetopessoas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.attornatus.projetopessoas.model.Person;
import com.attornatus.projetopessoas.repository.PersonRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;

	@GetMapping //Listagem de todas as pessoas
	public ResponseEntity<List<Person>> getAll() {
		return ResponseEntity.ok(personRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> getById(@PathVariable Long id) {
		return personRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/person/{name}")
	public ResponseEntity<List<Person>> getByName(@PathVariable String name){
		return ResponseEntity.ok(personRepository.findAllByNameContainingIgnoreCase(name));
	}
	
	@PostMapping
	public ResponseEntity<Person> post(@Valid @RequestBody Person person) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(personRepository.save(person));
	}
	
	@PutMapping
	public ResponseEntity<Person> put(@Valid @RequestBody Person person){
		return personRepository.findById(person.getId())
			.map(resposta -> ResponseEntity.status(HttpStatus.OK)
				.body(personRepository.save(person)))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Person> person = personRepository.findById(id);
			if(person.isEmpty())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			personRepository.deleteById(id);
	}
}