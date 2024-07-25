package com.example.sanket.LoginAndRegistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sanket.LoginAndRegistration.model.UserDtls;
import com.example.sanket.LoginAndRegistration.repository.UserRepository;
public interface UserService {

	
	public UserDtls createUser(UserDtls user);

	public boolean checkEmail(String email);
}
