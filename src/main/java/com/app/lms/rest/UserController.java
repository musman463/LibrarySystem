package com.app.lms.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.lms.entity.UserEntity;
import com.app.lms.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// expose "/users" and return list of users
	@GetMapping("/users")
	public List<UserEntity> findAll() {
		return userService.findAll();
	}

	// add mapping for GET /users/{userId}
	
	@GetMapping("/users/{userId}")
	public UserEntity getUser(@PathVariable int userId) {
		
		UserEntity theUser = userService.findById(userId);
		
		if (theUser == null) {
			throw new RuntimeException("UserEntity id not found - " + userId);
		}
		
		return theUser;
	}
	
	// add mapping for POST /users - add new user
	
	@PostMapping("/users")
	public UserEntity addUser(@RequestBody UserEntity theUser) {
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		theUser.setId(0);
		
		userService.save(theUser);
		
		return theUser;
	}
	
	// add mapping for PUT /users - update existing user
	
	@PutMapping("/users")
	public UserEntity updateUser(@RequestBody UserEntity theUser) {
		
		userService.save(theUser);
		
		return theUser;
	}
	
	// add mapping for DELETE /users/{userId} - delete user
	
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable int userId) {
		
		UserEntity tempUser = userService.findById(userId);
		
		// throw exception if null
		
		if (tempUser == null) {
			throw new RuntimeException("UserEntity id not found - " + userId);
		}
		
		userService.deleteById(userId);
		
		return "Deleted user id - " + userId;
	}	
	
}
