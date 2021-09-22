package com.nawaz2000.petshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.nawaz2000.petshop.dao.CartRepo;
import com.nawaz2000.petshop.dao.ContactUsRepo;
import com.nawaz2000.petshop.dao.OrdersRepo;
import com.nawaz2000.petshop.dao.ProductRepo;
import com.nawaz2000.petshop.dao.UserRepo;
import com.nawaz2000.petshop.dao.VetFinderRepo;
import com.nawaz2000.petshop.entity.Cart;
import com.nawaz2000.petshop.entity.ContactUs;
import com.nawaz2000.petshop.entity.Orders;
import com.nawaz2000.petshop.entity.Product;
import com.nawaz2000.petshop.entity.User;
import com.nawaz2000.petshop.entity.VetFinder;
import com.nawaz2000.petshop.service.UserCartProducts;
import com.nawaz2000.petshop.service.UserOrder;

@Controller
public class HomeController {
	
	private static int currUserId;
	private static User pUser;
	private ArrayList<UserCartProducts> userCartProducts;
	public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/images/uploads";
	
	public int getCurrUserId() {
		return currUserId;
	}
	
	@Autowired
	@Qualifier("vetFinderRepo")
	private VetFinderRepo vetRepo;
	
	@Autowired
	@Qualifier("productRepo")
	private ProductRepo productRepo;
	
	@Autowired
	@Qualifier("userRepo")
	private UserRepo userRepo;
	
	@Autowired
	@Qualifier("cartRepo")
	private CartRepo cartRepo;
	
	@Autowired
	@Qualifier("ordersRepo")
	private OrdersRepo ordersRepo;
	
	@Autowired
	@Qualifier("contactUsRepo")
	private ContactUsRepo contactUsRepo;
	
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
			
			userCartProducts = new ArrayList<>();
			
			List<Cart> currUserCart = cartRepo.findByUserId(currUser.getId());
			
			for (Cart c : currUserCart) {
				Product currProduct = productRepo.findById(c.getProductId()).get();
				this.userCartProducts.add(new UserCartProducts(c.getProductId(), c.getUserId(), c.getPrice(), c.getQuantity(), currProduct.getImage(), currProduct.getName()));
			}
			
			float totalPrice = 0;
			
			for (UserCartProducts u : this.userCartProducts) {
				totalPrice += u.getPrice()*u.getQuantity();
//				System.out.println(u);
			}
			
			model.addAttribute("userCartProducts", this.userCartProducts);
			model.addAttribute("totalPrice", totalPrice);
			System.out.println("------------>Total price: " + totalPrice);
			
		}
		
		List<Product> popularProduct = productRepo.findAll();
		for (Product p : popularProduct)
			System.out.println(p);
		model.addAttribute("popularProduct", popularProduct);
		
		if (currUsername.equals("admin"))
			return "redirect:/admin";
		
		return "index";
	}
	
	@GetMapping("/enquire")
	public String getEnquiryPage(Model model) {
		List<ContactUs> contactUsList = contactUsRepo.findAll();
		model.addAttribute("contactUsList", contactUsList);
		return "enquire";
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
		model.addAttribute("orders", new Orders());
		System.out.println("------------>Total price: " + totalPrice);
		
		return "checkout";
	}
	
	@PostMapping("/checkout")
	public String checkOut(@ModelAttribute Orders orders) {
		System.out.println("---------------> Checkout");
		System.out.println(orders);
//		ordersRepo.save(orders);
		
		return "index";
	}
	
	@GetMapping("/contactus")
	public String getContactUs(){
		return "contactus";
	}
	
	@GetMapping("/vetfinder")
	public String getVetFinder(Model model) {
		
		List<VetFinder> vetFinder = vetRepo.findAll();
		model.addAttribute("vetList", vetFinder);
		return "vetfinder";
	}
	
	@GetMapping("/account")
	public String getAccount(Model model) {
		model.addAttribute("userCartProducts", userCartProducts);
		return "account";
	}
	
	@PostMapping("/account")
	public String updateAccount(Model model, @ModelAttribute User user) {
		System.out.println("---------------->Retrieved user: " + user);
		userRepo.save(user);
		return "index";
	}
	
	@GetMapping("/orders")
	public String getOrders(Model model) {
		List<Orders> currUserOrders = ordersRepo.findByProfileId(currUserId);
		System.out.println("------------> CurrUserId: " + currUserId);
		
		List<UserOrder> orders = new ArrayList<>();
		
		for (Orders o : currUserOrders) {
			System.out.println(o);
			Product p = productRepo.findById(o.getProductId()).get();
			orders.add(new UserOrder(p.getName(), p.getImage(), o.getPrice(), o.getPayment(), o.getStatus()));
		}
		
		model.addAttribute("userCartProducts", userCartProducts);
		model.addAttribute("userOrders", orders);
		
		return "orders";
	}
	
}
