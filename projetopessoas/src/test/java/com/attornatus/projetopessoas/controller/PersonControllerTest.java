package com.attornatus.projetopessoas.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.attornatus.projetopessoas.model.Person;
import com.attornatus.projetopessoas.repository.PersonRepository;
import com.attornatus.projetopessoas.service.PersonService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private PersonRepository personRepository;
	
	@BeforeEach
	void start() {
		personRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Add a new person")
	public void addNewPerson() {
	
	
	HttpEntity<Person> request = new HttpEntity<Person>(new Person(0L, "Scarlet Martins", LocalDate.of(1995, 9, 29)));
	
	ResponseEntity<Person> response = testRestTemplate
			.exchange("/person/add", HttpMethod.POST, request, Person.class);
	
	assertEquals(HttpStatus.CREATED, response.getStatusCode());
	assertEquals(request.getBody().getName(), response.getBody().getName());
	assertEquals(request.getBody().getBirthDate(), response.getBody().getBirthDate());
	
	}
	
	@Test
	@DisplayName("Edit a person")
	public void editAPerson() {
		
		Optional<Person> personCreate = personService.addNewPerson(new Person(0L, "Scarlet Martins", LocalDate.of(1995, 9, 29)));
		
		Person personUpdate = new Person(personCreate.get().getId(), "Scarlet Martins", LocalDate.of(1995, 9, 29));
		
		HttpEntity<Person> request = new HttpEntity<Person>(personUpdate);
		
		ResponseEntity<Person> response = testRestTemplate
				.withBasicAuth("admin", "123")
				.exchange("/person/edit", HttpMethod.PUT, request, Person.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(request.getBody().getName(), response.getBody().getName());
		assertEquals(request.getBody().getBirthDate(), response.getBody().getBirthDate());
		
	}
	
	@Test
	@DisplayName("List Person")
	public void listPersons() {
		
		personService.addNewPerson(new Person(0L, "Scarlet Martins", LocalDate.of(1995, 9, 29)));
		
		personService.addNewPerson(new Person(0L, "John Brandão", LocalDate.of(1995, 9, 29)));
		
		ResponseEntity<String> response = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/person", HttpMethod.GET, null, String.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
