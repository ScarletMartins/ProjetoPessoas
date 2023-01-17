package com.attornatus.projetopessoas.controller;

import java.util.ArrayList;
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

import com.attornatus.projetopessoas.dto.AddressRequest;
import com.attornatus.projetopessoas.dto.AddressResponse;
import com.attornatus.projetopessoas.model.Address;
import com.attornatus.projetopessoas.model.Person;
import com.attornatus.projetopessoas.repository.AddressRepository;
import com.attornatus.projetopessoas.repository.PersonRepository;
import com.attornatus.projetopessoas.service.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AddressController {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping
	public ResponseEntity<List<AddressResponse>> getAll(){
		
		List<Address> addresses = addressRepository.findAll();
		
		List<AddressResponse> addressesResponse = new ArrayList<AddressResponse>();
		
		addresses.stream().forEach(address -> addressesResponse.add(AddressResponse.fromAddress(address))) ;
		
		return ResponseEntity.ok(addressesResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Address>> getAllById(@PathVariable Long id){
		
		return ResponseEntity.ok(addressRepository.findAllByPersonId(id));
	}
	
	@PostMapping("/add")
	public ResponseEntity<AddressResponse> addAddress(@Valid @RequestBody AddressRequest request) {
		
		addressService.uncheckMainAddress(request.getPersonId());
		
		Address address = new Address();
	    address.setStreet(request.getStreet());
	    address.setZipCode(request.getZipCode());
	    address.setNumber(request.getNumber());
	    address.setCity(request.getCity());
	    address.setMainAddress(request.isMainAddress());
	    	    
	    Optional<Person> personOptional = personRepository.findById(request.getPersonId());
	    if (!personOptional.isPresent()) {
	    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    	}
	    
	    address.setPerson(personOptional.get());
	    
	    Address addedAddress = addressRepository.save(address);
	    
	    AddressResponse response = new AddressResponse();
	    response.setId(addedAddress.getId());
	    response.setStreet(addedAddress.getStreet());
	    response.setZipCode(addedAddress.getZipCode());
	    response.setNumber(addedAddress.getNumber());
	    response.setCity(addedAddress.getCity());
	    response.setMainAddress(addedAddress.isMainAddress());
	    response.setPersonId(addedAddress.getPerson().getId());
	    
	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	
	@PutMapping("/edit/{id}")
	public ResponseEntity<AddressResponse> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressRequest request) {
		
		Optional<Person> personOptional = personRepository.findById(request.getPersonId());
	    if (!personOptional.isPresent()) {
	    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	    
	    Person personRetrieved = personOptional.get();
	    
		addressService.uncheckMainAddress(personRetrieved.getId());
		
	    Optional<Address> addressOptional = addressRepository.findById(id);
	    if (!addressOptional.isPresent()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    Address address = addressOptional.get();
	    address.setStreet(request.getStreet());
	    address.setZipCode(request.getZipCode());
	    address.setNumber(request.getNumber());
	    address.setCity(request.getCity());
	    address.setMainAddress(request.isMainAddress());
	    address.setPerson(personRetrieved);
	    

	    Address updatedAddress = addressRepository.save(address);

	    AddressResponse response = new AddressResponse();
	    response.setId(updatedAddress.getId());
	    response.setStreet(updatedAddress.getStreet());
	    response.setZipCode(updatedAddress.getZipCode());
	    response.setNumber(updatedAddress.getNumber());
	    response.setCity(updatedAddress.getCity());
	    response.setMainAddress(updatedAddress.isMainAddress());
	    response.setPersonId(personRetrieved.getId());

	    return new ResponseEntity<>(response, HttpStatus.OK);
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