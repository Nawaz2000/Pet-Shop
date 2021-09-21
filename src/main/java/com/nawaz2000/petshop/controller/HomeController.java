package com.nawaz2000.petshop.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
	
	private int currUserId;
	
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
	
	@PostMapping("/addToCart")
	public String addToCart(@RequestParam(name = "pro") String productId,
							@RequestParam(name = "userId", required = false) String userId,
							@RequestParam(name = "qty") String quantity, Model model, HttpServletRequest request) {
		System.out.println("\n\n\n\n------------------> Adding to cart");
		System.out.println("--------------------------> ProductId: " + productId + " Quantity: " + quantity + " UserId: " + userId);
		//System.out.println("ProductId: " + userCart.getProductId() + " Quantity: " + userCart.getQuantity() + " UserId: " + userCart.getUserId());
		float productPrice = productRepo.getById(Integer.parseInt(productId)).getPrice();
		Cart userCart = new Cart(Integer.parseInt(userId), Integer.parseInt(productId), productPrice, Integer.parseInt(quantity));
		System.out.println(userCart);
		cartRepo.save(userCart);
		
		return "redirect:/cart";
		
		
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
	
	@GetMapping("/cart")
	public String getCart(Model model) {
		
		List<UserCartProducts> ucp = new ArrayList<>();
		List<Cart> currCart = cartRepo.findByUserId(currUserId);
		for (Cart c : currCart) {
			Product currProduct = productRepo.getById(c.getProductId());
			ucp.add(new UserCartProducts(c.getProductId(), c.getUserId(), c.getPrice(), c.getQuantity(), currProduct.getImage(), currProduct.getName()));
		}
		model.addAttribute("userCartProducts", ucp);
		
		float totalPrice = 0;
		
		for (UserCartProducts u : ucp) {
			totalPrice += u.getPrice()*u.getQuantity();
//			System.out.println(u);
		}
		
		model.addAttribute("totalPrice", totalPrice);
		System.out.println("------------>Total price: " + totalPrice);
		
		return "cart";
	}
	
	@GetMapping("/deleteFromCart")
	public String deleteFromCart(@RequestParam String productId,
								@RequestParam String userId,
								@RequestParam String quantity,
								HttpServletRequest request) {
		System.out.println("\n\n---------------->Delete from cart: " + productId);
		Cart currCart = cartRepo.findByUserIdAndProductIdAndQuantity(Integer.parseInt(userId), Integer.parseInt(productId), Integer.parseInt(quantity));
		System.out.println(currCart);
		//cartRepo.deleteByUserIdAndProductIdAndQuantity(Integer.parseInt(userId), Integer.parseInt(productId), Integer.parseInt(quantity));
		cartRepo.delete(currCart);
		
		
		return "redirect:/cart";
		
		
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
