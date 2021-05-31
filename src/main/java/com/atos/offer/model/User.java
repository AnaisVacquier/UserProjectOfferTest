package com.atos.offer.model;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class User {

	private String firstName;
	private String lastName;
	private Integer age;
	private String email;
	private String phoneNumber;
	private Address address;

	public User() {
		super();
	}

	public User(String firstName, String lastName, Integer age, String email, String phoneNumber, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	@NotNull
	@NotEmpty
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@NotNull
	@NotEmpty
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@NotNull
	@Min(value = 18, message = "You must be older than 18yo to register")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Pattern(regexp = "^[\\w\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "The mail format is not valid")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Pattern(regexp = "[0-9]{10}", message = "The phone number should be 10 digits")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@NotNull
	@Valid
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
