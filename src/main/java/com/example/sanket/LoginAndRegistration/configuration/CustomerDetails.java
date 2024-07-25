package com.example.sanket.LoginAndRegistration.configuration;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.sanket.LoginAndRegistration.model.UserDtls;

/*
 Purpose: Provide user-specific data to Spring Security.
 */
public class CustomerDetails implements UserDetails{

	private UserDtls user;

	public CustomerDetails(UserDtls user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(user.getRole());
		return Arrays.asList(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}
	
	/*
Class and Constructor:
CustomerDetails: Implements UserDetails to provide user data.
CustomerDetails(UserDtls user): Constructor to initialize the user object.

Authorities:
getAuthorities(): Returns the user's role as a collection of authorities.

Credentials:
getPassword(): Returns the user's password.

	 */
	
}
