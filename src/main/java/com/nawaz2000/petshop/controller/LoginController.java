package com.nawaz2000.petshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


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
