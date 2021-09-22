package com.nawaz2000.petshop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nawaz2000.petshop.entity.Orders;

@Repository("ordersRepo")
public interface OrdersRepo extends JpaRepository<Orders, Integer> {
	
	public List<Orders> findByProfileId(int profileId);
	
}
