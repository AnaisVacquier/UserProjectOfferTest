package com.atos.offer.test;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Before;
import org.junit.Test;

import com.atos.offer.model.Address;
import com.atos.offer.model.User;
import com.atos.offer.service.UserService;

public class UserServiceTest {

	private User user;
	private Address address;

	@Before
	public void setupBeforeAll() {
		address = new Address("prado", "13000", "marseille", "france");
		user = new User("barack", "afritt", 34, "b.afritt@gmail.com", "0685147236", address);
	}

	@Test
	public void testValidatorsWhenUserIsOkay() {
		Set<ConstraintViolation<User>> constraintViolations = UserService.checkValidators(user);
		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testValidatorsWhenUserHasNoFirstName() {
		user.setFirstName("");
		Set<ConstraintViolation<User>> constraintViolations = UserService.checkValidators(user);
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testValidatorsWhenUserHasNoName() {
		user.setLastName("");
		Set<ConstraintViolation<User>> constraintViolations = UserService.checkValidators(user);
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testValidatorsWhenUserHasWrongAge() {
		user.setAge(17);
		Set<ConstraintViolation<User>> constraintViolations = UserService.checkValidators(user);
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testValidatorsWhenUserHasWrongemail() {
		user.setEmail("wrong-email");
		Set<ConstraintViolation<User>> constraintViolations = UserService.checkValidators(user);
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testValidatorsWhenUserHasWrongPhoneNumber() {
		user.setPhoneNumber("pronto");
		;
		Set<ConstraintViolation<User>> constraintViolations = UserService.checkValidators(user);
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testValidatorsWhenAddressHasWrongZipCode() {
		address.setZipCode("o6y82");
		;
		Set<ConstraintViolation<User>> constraintViolations = UserService.checkValidators(user);
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testValidatorsWhenAddressHasNotFranceCountry() {
		address.setCountry("portugal");
		;
		Set<ConstraintViolation<User>> constraintViolations = UserService.checkValidators(user);
		assertEquals(1, constraintViolations.size());
	}

}
