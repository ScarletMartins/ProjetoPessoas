package com.attornatus.projetopessoas.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.attornatus.projetopessoas.model.Person;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonRepositoryTest {

	@Autowired
	private PersonRepository personRepository;

	@BeforeEach
	void start() {
		personRepository.deleteAll();
		
		Person person = new Person(0L, "Scarlet Martins", LocalDate.of(1995, 9, 29));
	    person = personRepository.save(person);
	}
	
	@Test
	@DisplayName("Return person with the given Id")
	public void returnPersonsById() {
		
		Person person = new Person(0L, "Scarlet Martins", LocalDate.of(1995, 9, 29));
	    person = personRepository.save(person);
		
	Optional<Person> personId = personRepository.findById(person.getId());
	
	assertTrue(personId.isPresent());
	assertEquals(person.getId(), person.getId());
	}
}

