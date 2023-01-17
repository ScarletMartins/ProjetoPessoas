package com.attornatus.projetopessoas.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.attornatus.projetopessoas.dto.AddressRequest;
import com.attornatus.projetopessoas.model.Address;
import com.attornatus.projetopessoas.model.Person;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AddressRepositoryTest {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@BeforeEach
	void start() {
		
		addressRepository.deleteAll();
		
//		
//		Person person = new Person(0L, "Scarlet Martins", LocalDate.of(1995, 9, 29));
//	    person = personRepository.save(person);
//
//	    AddressRequest request = new AddressRequest();
//	    Address address = new Address();
//	    request.setStreet("Scenic Club Dr");
//	    request.setZipCode(43081);
//	    request.setNumber(1393);
//	    request.setCity("Westerville");
//	    request.setMainAddress(false);
//	    request.setPersonId(person.getId());
//	    
//	    addressRepository.save(address);

	}
	
	@Test
	@DisplayName("Return address with the given id")
	public void returnAddressById() {
		
	    Person person = new Person(0L, "Scarlet Martins", LocalDate.of(1995, 9, 29));
	    person = personRepository.save(person);

	    Address address1 = new Address(0L, "Scenic Club Dr", 43081, 1393, "Westerville", true, person);
	    Address address2 = new Address(0L, "Sunny Brook Dr", 43081, 1456, "Westerville", true, person);
	    address1 = addressRepository.save(address1);
	    address2 = addressRepository.save(address2);

	    Optional<List<Address>> addresses = addressRepository.findAddressById(person.getId());
	    assertTrue(addresses.isPresent());
	    assertEquals(1, addresses.get().size());
	    assertTrue(addresses.get().contains(address1));
	    assertTrue(addresses.get().contains(address2));
	}

}

