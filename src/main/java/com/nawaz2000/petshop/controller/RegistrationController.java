package com.nawaz2000.petshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nawaz2000.petshop.dao.UserRepo;
import com.nawaz2000.petshop.entity.User;

@Controller
public class RegistrationController {
	
	@Autowired
	@Qualifier(value = "userRepo")
	private UserRepo userRepo;
	
	@GetMapping("/register")
	public String getRegistrationPage(Model model) {
		model.addAttribute("newUser", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String saveNewUser(Model model, @ModelAttribute(name = "newUser") User user) {
		System.out.println(user);
		userRepo.save(user);
		return "index";
	}
	
}
