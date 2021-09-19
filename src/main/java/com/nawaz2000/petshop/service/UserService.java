package com.nawaz2000.petshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nawaz2000.petshop.dao.UserRepo;
import com.nawaz2000.petshop.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userDao;
	
	public void save(User user) {
		userDao.save(user);
	}
	
	public User findById(int id) {
		Optional<User> user = userDao.findById(id);
		if (user.isPresent())
			return user.get();
		else
			throw new RuntimeException("User with id: " + id + " not found");
	}
	
}
