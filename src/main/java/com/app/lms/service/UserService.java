package com.app.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.lms.dao.UserRepo;
import com.app.lms.entity.UserEntity;

@Service
public class UserService {

	private UserRepo userRepo;
	
	@Autowired
	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	public List<UserEntity> findAll() {
		return userRepo.findAll();
	}

	public UserEntity findById(int theId) {
		Optional<UserEntity> result = userRepo.findById(theId);
		
		UserEntity theUser = null;
		
		if (result.isPresent()) {
			theUser = result.get();
		}
		else {
			// we didn't find the user
			throw new RuntimeException("Did not find user id - " + theId);
		}
		
		return theUser;
	}

	public void save(UserEntity theUser) {
		userRepo.save(theUser);
	}

	public void deleteById(int theId) {
		userRepo.deleteById(theId);
	}
}
