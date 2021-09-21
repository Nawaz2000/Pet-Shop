package com.nawaz2000.petshop.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nawaz2000.petshop.entity.User;

@Repository("userRepo")
public interface UserRepo extends JpaRepository<User, Integer> {

	public Optional<User> findByEmail(String email);
	//public User findByEmail(String email);
	
	public Optional<User> findByUsername(String username);

}
