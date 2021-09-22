package com.nawaz2000.petshop.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nawaz2000.petshop.dao.ProductRepo;
import com.nawaz2000.petshop.entity.Product;

@Controller
public class AdminController {
	
	public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/images/uploads";
	
	@Autowired
	@Qualifier("productRepo")
	private ProductRepo productRepo;
	
	@GetMapping("/admin")
	public String showAdminPage(Model model, @RequestParam(name = "param", required = false) String param) {
		List<Product> products = productRepo.findAll();
		for (Product p : products)
			System.out.println(p);
		model.addAttribute("allProducts", products);
		model.addAttribute("product", new Product());
		return "admin";
	}
	
	@PostMapping("/admin")
	public String addProduct(@RequestParam("image") MultipartFile multipartFile,
							@RequestParam("name") String name,
							@RequestParam("id") String id,
							@RequestParam("category") String category,
							@RequestParam("descrip") String descrip,
							@RequestParam("price") String price) {
//		System.out.println(product);
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		fileName = "images/uploads/" + fileName;
		System.out.println("---------------> Image name: " + fileName);
		
		Product product = new Product(name, category, fileName, descrip, Float.parseFloat(price));
		product.setId(Integer.parseInt(id));
		System.out.println("\n\n----------->Obtained product: " + product);
		
		productRepo.save(product);
		
		StringBuilder fileNames = new StringBuilder();
		Path fileNameAndPath = Paths.get(uploadDirectory, multipartFile.getOriginalFilename());
		fileNames.append(multipartFile.getOriginalFilename()+" ");
		try {
			Files.write(fileNameAndPath, multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
	
}
