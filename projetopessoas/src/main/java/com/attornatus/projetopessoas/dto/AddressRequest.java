package com.attornatus.projetopessoas.dto;

public class AddressRequest {
	
	private Long id;
	
	private String street;

	private int zipCode;
	
	private int number;
	
	private String city;
	
	private boolean mainAddress;
	
	private Long personId;
	
	public AddressRequest() {
	}

	public AddressRequest(Long id, String street, int zipCode, int number, String city, boolean mainAddress, Long personId) {
		this.id = id;
		this.street = street;
		this.zipCode = zipCode;
		this.number = number;
		this.city = city;
		this.mainAddress = mainAddress;
		this.personId = personId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isMainAddress() {
		return mainAddress;
	}

	public void setMainAddress(boolean mainAddress) {
		this.mainAddress = mainAddress;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	
}
