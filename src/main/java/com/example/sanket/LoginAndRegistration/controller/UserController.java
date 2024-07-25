package com.example.sanket.LoginAndRegistration.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.sanket.LoginAndRegistration.model.UserDtls;
import com.example.sanket.LoginAndRegistration.repository.UserRepository;

import org.springframework.ui.Model;
@Controller
@RequestMapping("/user")
public class UserController {
	
	/*
	 Purpose: Handle web requests related to the user.
	 */
	
	@Autowired
	private UserRepository userRepo;
	
	@ModelAttribute
	public void userDetails(Model m,Principal p) {
		String email=p.getName();
		UserDtls user= userRepo.findByEmail(email);
		
		m.addAttribute("user",user);
	}
	
	/*
Model m: The Model object, used to pass data to the view. Spring MVC provides it to the method automatically.

The Principal object has a method getName(), which returns the name of the authenticated user. In this context, it is assumed to return the email of the user, which is being used as the username.

Principal p: The Principal object represents the currently authenticated user. It contains the user's security details, such as their username.

userRepo: This is a reference to the UserRepository, which is assumed to be an interface extending JpaRepository or another Spring Data repository.

findByEmail(email): This is a method provided by the UserRepository to find a user by their email address. It queries the database and returns a UserDtls object that matches the provided email.
	 */
	
	@GetMapping("/")
	public String home() {
		return "user/home";
	}
}
	
	
	/*
Class and Annotations:
UserController: Handles user-related web requests.
@Controller: Marks this class as a controller component.
@RequestMapping("/user"): Maps requests starting with /user to this controller.

Model Attribute Method:
userDetails(Model m, Principal p): Adds the authenticated user to the model.

Home Mapping:
home(): Returns the view name for the user's home page.
	 */
