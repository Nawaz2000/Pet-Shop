package com.nawaz2000.petshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nawaz2000.petshop.entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {

}
