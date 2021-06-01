package com.atos.offer.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.offer.log.LogManager;
import com.atos.offer.model.User;
import com.atos.offer.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("api/users")
public class UserController {

	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerUser(@RequestBody String userJson) throws IOException {
		ObjectMapper userMapper = new ObjectMapper();
		User user = userMapper.readValue(userJson, User.class);
		String invalidatorMessage = UserService.registerUser(user);
		if (invalidatorMessage != "") {
			String message = "The user has NOT been registered ! ";
			LogManager.writeLogs(user.toString());
			LogManager.writeLogs(message + invalidatorMessage);
			return new ResponseEntity<>(message + invalidatorMessage, HttpStatus.BAD_REQUEST);
		} else {
			String message = "The user has been registered";
			LogManager.writeLogs(user.toString());
			LogManager.writeLogs(message);
			return new ResponseEntity<>(message, HttpStatus.OK);
		}

	}

	@GetMapping(value = "/get/{id}")
	public ResponseEntity<String> getUser(@PathVariable(value = "id") String userId) throws IOException {
		String userJson = UserService.getUser(userId);
		if (userJson == "") {
			String message = "The requested user doesn't exist";
			LogManager.writeLogs("search for user id : " + userId);
			LogManager.writeLogs(message);
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
		String message = "request user with id :" + userId + " : " + userJson;
		LogManager.writeLogs(message);
		return new ResponseEntity<>(userJson, HttpStatus.OK);
	}
}
