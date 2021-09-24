package com.nawaz2000.petshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nawaz2000.petshop.dao.CartRepo;
import com.nawaz2000.petshop.dao.ProductRepo;
import com.nawaz2000.petshop.dao.UserRepo;
import com.nawaz2000.petshop.entity.Cart;
import com.nawaz2000.petshop.entity.Product;
import com.nawaz2000.petshop.entity.User;
import com.nawaz2000.petshop.service.UserCartProducts;

@Controller
public class CartController {

	private int currUserId;

	@Autowired
	@Qualifier("productRepo")
	private ProductRepo productRepo;

	@Autowired
	@Qualifier("cartRepo")
	private CartRepo cartRepo;

	@Autowired
	@Qualifier("userRepo")
	private UserRepo userRepo;

	@PostMapping("/addToCart")
	public String addToCart(@RequestParam(name = "pro") String productId,
			@RequestParam(name = "userId", required = false) String userId, @RequestParam(name = "qty") String quantity,
			Model model, HttpServletRequest request) {
		System.out.println("\n\n\n\n------------------> Adding to cart");
		System.out.println("--------------------------> ProductId: " + productId + " Quantity: " + quantity
				+ " UserId: " + userId);

		// System.out.println("ProductId: " + userCart.getProductId() + " Quantity: " +
		// userCart.getQuantity() + " UserId: " + userCart.getUserId());
		float productPrice = productRepo.getById(Integer.parseInt(productId)).getPrice();
		Cart userCart = new Cart(Integer.parseInt(userId), Integer.parseInt(productId), productPrice,
				Integer.parseInt(quantity));

		// Cart ifPresent =
		// cartRepo.findByUserIdAndProductIdAndQuantity(Integer.parseInt(userId),
		// Integer.parseInt(productId), Integer.parseInt(quantity));
		Cart ifPresent = new Cart();
		ifPresent = cartRepo.findByUserIdAndProductId(Integer.parseInt(userId), Integer.parseInt(productId));

		if (ifPresent != null) {

			System.out.println("--------> From cart: " + userCart);
			System.out.println("--------> From db: " + ifPresent);
			System.out.println(
					"UserCart pid: " + userCart.getProductId() + " IfPresent pid: " + ifPresent.getProductId());
			System.out
					.println("UserCart qty: " + ifPresent.getQuantity() + " IfPresent qty: " + ifPresent.getQuantity());

			if (userCart.getProductId() == ifPresent.getProductId()) {
				System.out.println("Match found: ");
				userCart.setId(ifPresent.getId());
			}
		}

		System.out.println("--------> From cart 2: " + userCart);
		cartRepo.save(userCart);

		return "redirect:/cart";

	}

	@GetMapping("/cart")
	public String getCart(Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String currUsername = "";

		if (principal instanceof UserDetails) {
			currUsername = ((UserDetails) principal).getUsername();
		} else {
			currUsername = principal.toString();
		}

		System.out.println("---------------------------> currUser: " + currUsername);

		if (!currUsername.equals("anonymousUser")) {
			User currUser = userRepo.findByUsername(currUsername).get();
			currUserId = currUser.getId();
		}

		List<UserCartProducts> ucp = new ArrayList<>();
		List<Cart> currCart = cartRepo.findByUserId(currUserId);
		for (Cart c : currCart) {
			Product currProduct = productRepo.getById(c.getProductId());
			ucp.add(new UserCartProducts(c.getProductId(), c.getUserId(), c.getPrice(), c.getQuantity(),
					currProduct.getImage(), currProduct.getName()));
		}
		model.addAttribute("userCartProducts", ucp);

		float totalPrice = 0;

		for (UserCartProducts u : ucp) {
			totalPrice += u.getPrice() * u.getQuantity();
//			System.out.println(u);
		}

		model.addAttribute("totalPrice", totalPrice);
		System.out.println("------------>Total price: " + totalPrice);

		return "cart";
	}

	@GetMapping("/deleteFromCart")
	public String deleteFromCart(@RequestParam String productId, @RequestParam String userId,
			@RequestParam String quantity, HttpServletRequest request) {
		System.out.println("\n\n---------------->Delete from cart: " + productId);
		Cart currCart = cartRepo.findByUserIdAndProductIdAndQuantity(Integer.parseInt(userId),
				Integer.parseInt(productId), Integer.parseInt(quantity));
		System.out.println(currCart);
		// cartRepo.deleteByUserIdAndProductIdAndQuantity(Integer.parseInt(userId),
		// Integer.parseInt(productId), Integer.parseInt(quantity));
		cartRepo.delete(currCart);

		return "redirect:/cart";

	}

}
