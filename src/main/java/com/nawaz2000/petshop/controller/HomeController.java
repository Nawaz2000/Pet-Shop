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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nawaz2000.petshop.dao.CartRepo;
import com.nawaz2000.petshop.dao.OrdersRepo;
import com.nawaz2000.petshop.dao.ProductRepo;
import com.nawaz2000.petshop.dao.UserRepo;
import com.nawaz2000.petshop.dao.VetFinderRepo;
import com.nawaz2000.petshop.entity.Cart;
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
	
	@GetMapping("/admin")
	public String showAdminPage(Model model, @RequestParam(name = "param", required = false) String param) {
		List<Product> products = productRepo.findAll();
		for (Product p : products)
			System.out.println(p);
		model.addAttribute("allProducts", products);

//		if (param != null) {
//			if (param.equals(""))
//		}
		
		return "admin";
	}
	
	@PostMapping("/admin")
	public String addProduct(@RequestParam("image") MultipartFile multipartFile,
							@RequestParam("name") String name,
							@RequestParam("category") String category,
							@RequestParam("descrip") String descrip,
							@RequestParam("price") String price) {
//		System.out.println(product);
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		fileName = "images/uploads/" + fileName;
		System.out.println("---------------> Image name: " + fileName);
		
		Product product = new Product(name, category, fileName, descrip, Float.parseFloat(price));
		System.out.println("\n\n----------->Obtained product: " + product);
		
		productRepo.save(product);
		
		return "redirect:/admin";
	}
	
	@GetMapping("/updateProduct")
	public String showUpdateProductPage(@RequestParam(name = "param") String param, Model model) {
		
		Product product = productRepo.findById(Integer.parseInt(param)).get();
		model.addAttribute("updateProduct", product);
		
		return "product-update";
	}
	
	@GetMapping("/removeProduct")
	public String deleteProduct(@RequestParam(name = "param") String param, Model model) {
		Product product = productRepo.findById(Integer.parseInt(param)).get();
		System.out.println("-------------> Product to be deleted: " + product);
		productRepo.delete(product);
		return "redirect:/admin";
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
