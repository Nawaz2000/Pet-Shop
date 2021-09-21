package com.nawaz2000.petshop.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nawaz2000.petshop.entity.Product;

@Repository("productRepo")
public interface ProductRepo extends JpaRepository<Product, Integer> {
	
	public List<Product> findByCategory(String category);
	
}
