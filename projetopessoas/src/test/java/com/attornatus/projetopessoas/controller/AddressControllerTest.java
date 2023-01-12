package com.attornatus.projetopessoas.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.attornatus.projetopessoas.model.Address;
import com.attornatus.projetopessoas.repository.AddressRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddressControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@BeforeAll
	void start() {
		addressRepository.deleteAll();
	}
	
	@Test
	@Order(1)
	@DisplayName("Add a new address")
	public void addANewAddress() {

		HttpEntity<Address> request = new HttpEntity<Address>(new Address(0L, "Scenic Club Dr", 43081, 1393, "Westerville", false));
		
		ResponseEntity<Address> response = testRestTemplate
				.exchange("/address/add", HttpMethod.POST, request, Address.class);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(request.getBody().getStreet(), response.getBody().getStreet());
		assertEquals(request.getBody().getZipCode(), response.getBody().getZipCode());
		assertEquals(request.getBody().getNumber(), response.getBody().getNumber());
		assertEquals(request.getBody().isMainAddress(), response.getBody().isMainAddress());

	}
}
