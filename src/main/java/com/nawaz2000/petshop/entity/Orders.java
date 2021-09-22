package com.nawaz2000.petshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "profile_id")
	private int profileId;
	
	@Column(name = "product_id")
	private int productId;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "qty")
	private int qty;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "district")
	private String district;
	
	@Column(name = "postcode")
	private String postCode;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "payment")
	private String payment;
	
	@Column(name = "status")
	private String status;
	
	public Orders() {
		
	}

	public Orders(int profileId, int productId, int price, int qty, String country, String district, String postCode,
			String address, String payment, String status) {
		super();
		this.profileId = profileId;
		this.productId = productId;
		this.price = price;
		this.qty = qty;
		this.country = country;
		this.district = district;
		this.postCode = postCode;
		this.address = address;
		this.payment = payment;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		return "Orders [id=" + id + ", profileId=" + profileId + ", productId=" + productId + ", price=" + price
				+ ", qty=" + qty + ", country=" + country + ", district=" + district + ", postCode=" + postCode
				+ ", address=" + address + ", payment=" + payment + ", status=" + status + "]";
	}
	
}
