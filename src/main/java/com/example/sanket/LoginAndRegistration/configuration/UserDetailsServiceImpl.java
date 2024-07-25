package com.example.sanket.LoginAndRegistration.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.sanket.LoginAndRegistration.model.UserDtls;
import com.example.sanket.LoginAndRegistration.repository.UserRepository;

@Service 						// UserDetailsService is External Interface.
public class UserDetailsServiceImpl implements UserDetailsService{

	/*
	 Purpose: Load user data from the database.
	 */
	
	@Autowired
	private UserRepository userRepo;
	
	// Checks whether user email is present in database or not.
	@Override
	public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException{
		
		UserDtls user=userRepo.findByEmail(email);  // from UserRepository.Java
		
		if(user!=null) {
			return new CustomerDetails(user);  // from CustomerDetails.java
		}
		
		throw new UsernameNotFoundException("user not available");
	}
	
	/*
Class and Annotations:
UserDetailsServiceImpl: Implements UserDetailsService to load user data.
@Service: Marks this class as a service component.

Loading User by Username:
loadUserByUsername(String email): Fetches user by email and returns a CustomerDetails object. Throws exception if user is not found.
	 */
}
