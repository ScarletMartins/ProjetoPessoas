package com.attornatus.projetopessoas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.attornatus.projetopessoas.dto.AddressRequest;
import com.attornatus.projetopessoas.model.Address;
import com.attornatus.projetopessoas.model.Person;
import com.attornatus.projetopessoas.repository.AddressRepository;
import com.attornatus.projetopessoas.repository.PersonRepository;

import jakarta.validation.Valid;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	public void uncheckMainAddress(Long personId) {
		
		List<Address> addresses = addressRepository.findAllByPersonId(personId);
		
		addresses.stream().filter(mainAddress -> mainAddress.isMainAddress() == true).forEach(a -> a.setMainAddress(false));

	}
	
	public ResponseEntity<Address> updatedAddress(@Valid AddressRequest request) {
		
		 Optional<Person> personOptional = personRepository.findById(request.getPersonId());
		    if (!personOptional.isPresent()) {
		        return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
		    }
		    
		Address address = new Address();
		address.setId(request.getId());
		address.setStreet(request.getStreet());
		address.setZipCode(request.getZipCode());
		address.setNumber(request.getNumber());
	    address.setCity(request.getCity());
	    address.setMainAddress(request.isMainAddress());
		
		if(addressRepository.findAddressById(request.getId()).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found!");
		}
			
		addressRepository.save(address);
		return null;
	}


}










