package com.example.sanket.LoginAndRegistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

import com.example.sanket.LoginAndRegistration.model.UserDtls;
import com.example.sanket.LoginAndRegistration.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	@PostMapping("/createUser")
	public String createUser(@ModelAttribute UserDtls user,HttpSession session) {
		
		boolean f= userService.checkEmail(user.getEmail());
		
		if(f) {
			session.setAttribute("msg","Email Id already exists");
		}else {
			UserDtls userDtls= userService.createUser(user);
			if(userDtls!=null) {
				session.setAttribute("msg","Registration Successfully");
			}else {
				session.setAttribute("msg","Something Error in the server");
			}
		}
		return "redirect:/register";
	}
}
