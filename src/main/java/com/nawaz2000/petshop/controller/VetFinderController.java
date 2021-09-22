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

import com.nawaz2000.petshop.dao.VetFinderRepo;
import com.nawaz2000.petshop.entity.VetFinder;

@Controller
public class VetFinderController {
	
	public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/images/uploads";
	
	@Autowired
	@Qualifier("vetFinderRepo")
	private VetFinderRepo vetRepo;
	
	@GetMapping("/addvetfinder")
	public String showVet(Model model) {
		List<VetFinder> vetFinderList = vetRepo.findAll();
		model.addAttribute("vetList", vetFinderList);
		model.addAttribute("vetFinder", new VetFinder());
		return "addvetfinder";
	}
	
	@GetMapping("/updateVet")
	public String updateVet(@RequestParam(name = "param") String param, Model model) {
		VetFinder vetFinder = vetRepo.findById(Integer.parseInt(param)).get();
		
		model.addAttribute("updateVet", vetFinder);
		return "vet-update";
	}
	
	@PostMapping("/addvetfinder")
	public String addVet(@RequestParam("pic") MultipartFile multipartFile,
						@RequestParam("name") String name,
						@RequestParam("id") String id,
						@RequestParam("email") String email,
						@RequestParam("phone") String phone,
						@RequestParam("address") String address,
						@RequestParam("note") String note) throws IOException {
		
		
		
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		fileName = "images/uploads/" + fileName;
//		
		VetFinder vetFinder = new VetFinder(name, email, phone, address, note, fileName);
		vetFinder.setId(Integer.parseInt(id));
		vetRepo.save(vetFinder);
		
		System.out.println("-----------------newVetFinder" + vetFinder);
		
		
		StringBuilder fileNames = new StringBuilder();
		Path fileNameAndPath = Paths.get(uploadDirectory, multipartFile.getOriginalFilename());
		fileNames.append(multipartFile.getOriginalFilename()+" ");
		try {
			Files.write(fileNameAndPath, multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/addvetfinder";
	}
	
	@GetMapping("/removeVet")
	public String removeVet(@RequestParam(name = "param") String param, Model model) {
		VetFinder vetFinder = vetRepo.findById(Integer.parseInt(param)).get();
		vetRepo.delete(vetFinder);
		return "redirect:/addvetfinder";
	}
	
}
