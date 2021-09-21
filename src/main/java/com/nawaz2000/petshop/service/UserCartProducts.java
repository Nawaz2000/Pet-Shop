package com.nawaz2000.petshop.service;

import javax.persistence.Column;

public class UserCartProducts {
	
	private float price;

	private int quantity;
	
	private String image;
	
	private String name;
	
	public UserCartProducts() {
		
	}

	public UserCartProducts(float price, int quantity, String image, String name) {
		super();
		this.price = price;
		this.quantity = quantity;
		this.image = image;
		this.name = name;
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
