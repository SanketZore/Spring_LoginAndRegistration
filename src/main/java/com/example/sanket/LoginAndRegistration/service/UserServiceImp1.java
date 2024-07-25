package com.example.sanket.LoginAndRegistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sanket.LoginAndRegistration.model.UserDtls;
import com.example.sanket.LoginAndRegistration.repository.UserRepository;
@Service
public class UserServiceImp1{ 
//public class UserServiceImp1 implements UserService{  // from UserService.java

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncode;
	
//	@Override
	public UserDtls createUser(UserDtls user) {   // normal saving user data to DB.
		user.setPassword(passwordEncode.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		return userRepository.save(user);
	}

//	@Override
	public boolean checkEmail(String email) {
		return userRepository.existsByEmail(email);
	}

}
	
	/*
Class and Annotations:
UserServiceImpl: Implements UserService to handle user operations.
@Service: Marks this class as a service component.

Creating User:
createUser(UserDtls user): Encrypts the user's password, sets the default role, and saves the user.

Checking Email:
checkEmail(String email): Checks if an email already exists in the database.
	 */


