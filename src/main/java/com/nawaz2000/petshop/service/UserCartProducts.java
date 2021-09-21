package com.nawaz2000.petshop.service;

import javax.persistence.Column;

public class UserCartProducts {
	
	private int productId;
	
	private int userId;
	
	private float price;

	private int quantity;
	
	private String image;
	
	private String name;
	
	private float totalPrice;
	
	public UserCartProducts() {
		
	}

	public UserCartProducts(int productId, int userId, float price, int quantity, String image, String name) {
		super();
		this.productId = productId;
		this.userId = userId;
		this.price = price;
		this.quantity = quantity;
		this.image = image;
		this.name = name;
		this.totalPrice = this.price * this.quantity;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserCartProducts [price=" + price + ", quantity=" + quantity + ", image=" + image + ", name=" + name
				+ "]";
	}
	
}
