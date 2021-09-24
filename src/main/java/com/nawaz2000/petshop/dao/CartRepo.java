package com.nawaz2000.petshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nawaz2000.petshop.entity.Cart;

@Repository("cartRepo")
public interface CartRepo extends JpaRepository<Cart, Integer> {
	
	public List<Cart> findByUserId(int userid);
	
	public Cart deleteByUserIdAndProductIdAndQuantity(int userId, int productId, int quantity);
	
	public Cart findByUserIdAndProductIdAndQuantity(int userId, int productId, int quantity);
	
	public Cart findByUserIdAndProductId(int userId, int productId);
	
}
