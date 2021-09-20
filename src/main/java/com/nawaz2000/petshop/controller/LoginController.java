package com.nawaz2000.petshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nawaz2000.petshop.entity.User;

@Controller
public class LoginController {
	
//	@Autowired
//	private UserService userService;
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("userModel", new User());
		return "login";
	}
	
	
}
