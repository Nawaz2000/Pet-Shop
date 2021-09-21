package com.nawaz2000.petshop.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nawaz2000.petshop.dao.CartRepo;
import com.nawaz2000.petshop.dao.ProductRepo;
import com.nawaz2000.petshop.dao.UserRepo;
import com.nawaz2000.petshop.entity.Cart;
import com.nawaz2000.petshop.entity.Product;
import com.nawaz2000.petshop.entity.User;
import com.nawaz2000.petshop.service.UserCartProducts;

@Controller
public class HomeController {
	
	private static int currUserId;
	private static User pUser;
	
	public int getCurrUserId() {
		return currUserId;
	}
	
	@Autowired
	@Qualifier("productRepo")
	private ProductRepo productRepo;
	
	@Autowired
	@Qualifier("userRepo")
	private UserRepo userRepo;
	
	@Autowired
	@Qualifier("cartRepo")
	private CartRepo cartRepo;
	
	public void addToModel() {
		
	}
	
	@GetMapping({"/","/index"})
	public String getHome(Model model, HttpSession session) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String currUsername = "";
		
		if (principal instanceof UserDetails) {
		  currUsername = ((UserDetails)principal).getUsername();
		} else {
		  currUsername = principal.toString();
		}
		
		System.out.println("---------------------------> currUser: " + currUsername);
		
		if (!currUsername.equals("anonymousUser")) {
			User currUser = userRepo.findByUsername(currUsername).get();
			System.out.println("----------------------------> CurrUser id: " + currUser.getId());
			
			model.addAttribute("currUser", currUser);
			session.setAttribute("user", currUser);
			currUserId = currUser.getId();
			pUser = currUser;
			
			List<Cart> currUserCart = cartRepo.findByUserId(currUser.getId());
			
			ArrayList<UserCartProducts> userCartProducts = new ArrayList<>();
			
			for (Cart c : currUserCart) {
				Product currProduct = productRepo.findById(c.getProductId()).get();
				userCartProducts.add(new UserCartProducts(c.getProductId(), c.getUserId(), c.getPrice(), c.getQuantity(), currProduct.getImage(), currProduct.getName()));
			}
			
			float totalPrice = 0;
			
			for (UserCartProducts u : userCartProducts) {
				totalPrice += u.getPrice()*u.getQuantity();
//				System.out.println(u);
			}
			
			model.addAttribute("userCartProducts", userCartProducts);
			model.addAttribute("totalPrice", totalPrice);
			System.out.println("------------>Total price: " + totalPrice);
			
		}
		
		List<Product> popularProduct = productRepo.findAll();
		for (Product p : popularProduct)
			System.out.println(p);
		model.addAttribute("popularProduct", popularProduct);
	
		return "index";
	}
	
	@GetMapping("/singleproduct{id}")
	public String showSingleProduct(@PathParam("id") String id, Model model) {
		System.out.println("-------------------> id: " + id);
		
		Product productById = productRepo.getById(Integer.parseInt(id));
		System.out.println("------------> Single Product: " + productById);
		model.addAttribute("singleProduct", productById);
		model.addAttribute("userCart", new Cart());
		return "singleproduct";
		
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		
		List<Cart> currUserCart = cartRepo.findByUserId(pUser.getId());
		
		ArrayList<UserCartProducts> userCartProducts = new ArrayList<>();
		
		for (Cart c : currUserCart) {
			Product currProduct = productRepo.findById(c.getProductId()).get();
			userCartProducts.add(new UserCartProducts(c.getProductId(), c.getUserId(), c.getPrice(), c.getQuantity(), currProduct.getImage(), currProduct.getName()));
		}
		
		float totalPrice = 0;
		
		for (UserCartProducts u : userCartProducts) {
			totalPrice += u.getPrice()*u.getQuantity();
//			System.out.println(u);
		}
		
		model.addAttribute("userCartProducts", userCartProducts);
		model.addAttribute("totalPrice", totalPrice);
		System.out.println("------------>Total price: " + totalPrice);
		
		return "checkout";
	}
	
	@GetMapping("/contactus")
	public String getContactUs(){
		return "contactus";
	}
	
	@GetMapping("/vetfinder")
	public String getVetFinder() {
		return "vetfinder";
	}
	
	@GetMapping("/account")
	public String getAccount() {
		return "account";
	}
	
	@GetMapping("/orders")
	public String getOrders() {
		return "orders";
	}
	
}
