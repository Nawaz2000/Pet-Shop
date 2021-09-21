package com.nawaz2000.petshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nawaz2000.petshop.dao.ProductRepo;
import com.nawaz2000.petshop.entity.Product;

@Controller
public class StoreController {
		
	@Autowired
	@Qualifier("productRepo")
	private ProductRepo productRepo;	
	
	@GetMapping("/store")
	public String getStore(@RequestParam(name = "param") String storeQuery, Model model) {
		System.out.println("-------------------> " + storeQuery);
		
		List<Product> storeProduct = new ArrayList<>();
		
		if (storeQuery.equals("All"))
			storeProduct = productRepo.findAll();
		else
			storeProduct = productRepo.findByCategory(storeQuery);
		
		model.addAttribute("storeProduct", storeProduct);
		
		
		return "store";
	}
	
	@PostMapping("/store")
	public String filterStoreByPrice(Model model,
			@RequestParam(name = "amount") String amount) {
		
		System.out.println(amount);
		String[] limit = amount.split("-");
		
		float startPrice = Float.parseFloat(limit[0]);
		float endPrice = Float.parseFloat(limit[1]);
		
		System.out.println("\n\n------>Start price: " + startPrice + " End price: " + endPrice);
		
		List<Product> storeProduct = new ArrayList<>();
		
//		if (storeQuery.equals("All"))
			storeProduct = productRepo.findByPriceBetween(startPrice, endPrice);
		
		System.out.println("===================>Product within price:");
			for (Product p : storeProduct)
				System.out.println(p);
//		else
//			storeProduct = productRepo.findByCategoryAndPriceBetween(storeQuery, startPrice, endPrice);
		
		model.addAttribute("storeProduct", storeProduct);
		
		
		
		//return "redirect:/store?param=All";
		return "store";
		
	}
	
}
