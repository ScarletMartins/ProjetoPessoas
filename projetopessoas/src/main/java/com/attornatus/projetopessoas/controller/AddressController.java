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

import com.attornatus.projetopessoas.model.Address;
import com.attornatus.projetopessoas.repository.AddressRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AddressController {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@GetMapping
	public ResponseEntity<List<Address>> getAll(){
		return ResponseEntity.ok(addressRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Address>> getAllById(@PathVariable Long id){
		return ResponseEntity.ok(addressRepository.findAllByPersonId(id));
	}
	
	@PostMapping("/add")
	public ResponseEntity<Address> post(@Valid @RequestBody Address address){
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(addressRepository.save(address));
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Address> put(@Valid @RequestBody Address address){
		return addressRepository.findById(address.getId()).map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
				.body(addressRepository.save(address)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Address> address = addressRepository.findById(id);
			if(address.isEmpty())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			addressRepository.deleteById(id);
	}
	
}