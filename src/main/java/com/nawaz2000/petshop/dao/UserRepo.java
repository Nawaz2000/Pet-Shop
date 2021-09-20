package com.nawaz2000.petshop.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nawaz2000.petshop.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	public Optional<User> findByEmail(String email);
	//public User findByEmail(String email);
	
	public Optional<User> findByUsername(String username);

}
