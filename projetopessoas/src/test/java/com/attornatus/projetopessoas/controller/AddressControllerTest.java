package com.attornatus.projetopessoas.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

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

import com.attornatus.projetopessoas.dto.AddressRequest;
import com.attornatus.projetopessoas.dto.AddressResponse;
import com.attornatus.projetopessoas.model.Address;
import com.attornatus.projetopessoas.model.Person;
import com.attornatus.projetopessoas.repository.AddressRepository;
import com.attornatus.projetopessoas.repository.PersonRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddressControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@BeforeEach
	void start() {
		addressRepository.deleteAll();
	}
	
//	@Test
//	@DisplayName("Add a new address")
//	public void addANewAddress() {
//		
//		// Create a new person
//	    Person person = new Person(0L, "Scarlet Martins", LocalDate.of(1995, 9, 29));
//	    person = personRepository.save(person);
//
//	    // Create a new address request
//	    AddressRequest request = new AddressRequest();
//	    request.setStreet("Scenic Club Dr");
//	    request.setZipCode(43081);
//	    request.setNumber(1393);
//	    request.setCity("Westerville");
//	    request.setMainAddress(false);
//	    request.setPersonId(person.getId());
//
//	    // Send the request to the add method
//	    ResponseEntity<AddressResponse> response = testRestTemplate
//	            .postForEntity("/address/add", request, AddressResponse.class);
//
//	    // Assert the response
//	    assertEquals(HttpStatus.CREATED, response.getStatusCode());
//	    assertEquals(request.getStreet(), response.getBody().getStreet());
//	    assertEquals(request.getZipCode(), response.getBody().getZipCode());
//	    assertEquals(request.getNumber(), response.getBody().getNumber());
//	    assertEquals(request.isMainAddress(), response.getBody().isMainAddress());
//	    assertEquals(request.getPersonId(), response.getBody().getPersonId());
//	}

}


