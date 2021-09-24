package com.nawaz2000.petshop.service;

import java.util.Arrays;

import javax.persistence.Column;

public class UserCartOrders {
	
	private int profileId;//

	private int[] productId;

	private float[] price;

	private int[] qty;
	
	private String fName;//
	
	private String lName;//
	
	private String phone;//

	private String country;//

	private String district;//

	private String postCode;//

	private String address;//

	private String payment;

	private String status;

	public UserCartOrders() {
		super();
	}

	public UserCartOrders(int profileId, int[] productId, float[] price, int[] qty, String fName, String lName,
			String phone, String country, String district, String postCode, String address, String payment,
			String status) {
		super();
		this.profileId = profileId;
		this.productId = productId;
		this.price = price;
		this.qty = qty;
		this.fName = fName;
		this.lName = lName;
		this.phone = phone;
		this.country = country;
		this.district = district;
		this.postCode = postCode;
		this.address = address;
		this.payment = payment;
		this.status = status;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public int[] getProductId() {
		return productId;
	}

	public void setProductId(int[] productId) {
		this.productId = productId;
	}

	public float[] getPrice() {
		return price;
	}

	public void setPrice(float[] price) {
		this.price = price;
	}

	public int[] getQty() {
		return qty;
	}

	public void setQty(int[] qty) {
		this.qty = qty;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
		return "UserCartOrders [profileId=" + profileId + ", productId=" + Arrays.toString(productId) + ", price="
				+ Arrays.toString(price) + ", qty=" + Arrays.toString(qty) + ", fName=" + fName + ", lName=" + lName
				+ ", phone=" + phone + ", country=" + country + ", district=" + district + ", postCode=" + postCode
				+ ", address=" + address + ", payment=" + payment + ", status=" + status + "]";
	}
	
	
	
}
