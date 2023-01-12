package com.attornatus.projetopessoas.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private PersonRepository personRepository;
	
	@BeforeAll
	void start() {
		personRepository.deleteAll();
	}
	
	LocalDate date = LocalDate.of(1995, 9, 29);
	
	@Test
	@Order(1)
	@DisplayName("Add a new person")
	public void addNewPerson() {
	
	
	HttpEntity<Person> request = new HttpEntity<Person>(new Person(0L, "Scarlet Martins", date));
	
	ResponseEntity<Person> response = testRestTemplate
			.exchange("/person/add", HttpMethod.POST, request, Person.class);
	
	assertEquals(HttpStatus.CREATED, response.getStatusCode());
	assertEquals(request.getBody().getName(), response.getBody().getName());
	assertEquals(request.getBody().getBirthDate(), response.getBody().getBirthDate());
	
	}
	
	@Test
	@Order(2)
	@DisplayName("Do not allow duplicate person")
	public void doNotAllowDuplicatePerson() {
	
		personService.addNewPerson(new Person(0L, "Scarlet Martins", date));
		
		HttpEntity<Person> request = new HttpEntity<Person>(new Person(0L, "Scarlet Martins", date));
		
		ResponseEntity<Person> response = testRestTemplate
				.exchange("/person/add", HttpMethod.POST, request, Person.class);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		
	}
	
}
