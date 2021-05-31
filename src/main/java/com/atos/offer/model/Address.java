package com.atos.offer.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Address {

	private String street;
	private String zipCode;
	private String city;
	private String country;

	public Address() {
		super();
	}

	public Address(String street, String zipCode, String city, String country) {
		super();
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Pattern(regexp = "[0-9]{5}", message = "The zipCode should be 5 digits")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@NotNull
	@Pattern(regexp = "(?i)france", message = "You must live in France to create an account")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
