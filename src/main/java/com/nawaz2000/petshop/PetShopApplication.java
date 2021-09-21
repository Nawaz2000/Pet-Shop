package com.nawaz2000.petshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.nawaz2000.petshop.dao.UserRepo;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.nawaz2000.petshop")
public class PetShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetShopApplication.class, args);
	}

}
