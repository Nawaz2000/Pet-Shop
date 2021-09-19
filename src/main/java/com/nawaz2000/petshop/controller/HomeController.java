package com.nawaz2000.petshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@GetMapping({"/","/index"})
	public String getHome() {
		return "index";
	}
	
	@GetMapping("/contactus")
	public String getContactUs(){
		return "contactus";
	}
	
	@GetMapping("/vetfinder")
	public String getVetFinder() {
		return "vetfinder";
	}
	
	@GetMapping("/store")
	public String getStore(@RequestParam(name = "param") String storeQuery) {
		System.out.println("-------------------> " + storeQuery);
		return "store";
	}
	
}
