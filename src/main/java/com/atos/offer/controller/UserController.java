package com.atos.offer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.offer.model.User;
import com.atos.offer.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("api/users")
public class UserController {

	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerUser(@RequestBody String userJson)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper userMapper = new ObjectMapper();
		User user = userMapper.readValue(userJson, User.class);
		UserService.registerUser(user);
		return new ResponseEntity<>("The user has been registered", HttpStatus.OK);
	}

	@GetMapping(value = "/get/{id}")
	public ResponseEntity<String> getUser(@PathVariable(value = "id") String userId) {
		String userJson = UserService.getUser(userId);
		if (userJson == "") {
			return new ResponseEntity<>("The requested user doesn't exist", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(userJson, HttpStatus.OK);
	}
}
