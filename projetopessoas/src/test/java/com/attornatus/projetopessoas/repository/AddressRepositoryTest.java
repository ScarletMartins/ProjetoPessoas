package com.attornatus.projetopessoas.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

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

import com.attornatus.projetopessoas.model.Address;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressRepositoryTest {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@BeforeAll
	void start() {
		
		addressRepository.deleteAll();
		
		addressRepository.save(new Address(0L, "Scenic Club Dr", 43081, 1393, "Westerville", false));
		
		addressRepository.save(new Address(1L, "João Rodrigues", 290954, 55, "São Paulo", true));
		
		addressRepository.save(new Address(0L, "José Tanoeiro", 861323, 783, "Suzano", false));
	}
	
	@Test
	@Order(1)
	@DisplayName("Returns the address by City")
	public void returnAddressByCity() {
		
		Optional<Address> address = addressRepository.findByCity("São Paulo");
		
		assertTrue(address.get().getCity().equals("São Paulo"));
	}

}
