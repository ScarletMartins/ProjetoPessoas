package com.attornatus.projetopessoas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String street;
	
	@Column(nullable = false)
	private int zipCode;
	
	@Column(nullable = false)
	private int number;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private boolean mainAddress = true;
	
	@ManyToOne
	@JsonIgnoreProperties("address")
	@JoinColumn(referencedColumnName = "id")
	private Person person;
	
	public Address() {
	}
	
	public Address(Long id, String street,int zipCode, int number, String city, boolean mainAddress) {
		this.id = id;
		this.street = street;
		this.zipCode = zipCode;
		this.number = number;
		this.city = city;
		this.mainAddress = mainAddress;
	}

	public Address(Long id, String street, int zipCode, int number, String city,
			boolean mainAddress, Person personId) {
		this.id = id;
		this.street = street;
		this.zipCode = zipCode;
		this.number = number;
		this.city = city;
		this.mainAddress = mainAddress;
		this.person = personId;
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}


}
