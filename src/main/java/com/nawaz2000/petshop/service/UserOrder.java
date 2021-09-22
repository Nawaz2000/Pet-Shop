package com.nawaz2000.petshop.service;

public class UserOrder {
	
	private String name;
	private String image;
	private float price;
	private String payment;
	private String status;
	
	public UserOrder() {
		
	}

	public UserOrder(String name, String image, float price, String payment, String status) {
		super();
		this.name = name;
		this.image = image;
		this.price = price;
		this.payment = payment;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserOrder [name=" + name + ", image=" + image + ", price=" + price + ", payment=" + payment
				+ ", status=" + status + "]";
	}

	
	
	
	
}
