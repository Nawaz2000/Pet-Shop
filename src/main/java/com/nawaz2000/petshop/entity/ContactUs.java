package com.nawaz2000.petshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contactus")
public class ContactUs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "fname")
	private String fName;
	
	@Column(name = "lname")
	private String lName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "msg")
	private String msg;
	
	public ContactUs() {
		
	}

	public ContactUs(String fName, String lName, String email, String subject, String msg) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.subject = subject;
		this.msg = msg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ContactUs [id=" + id + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", subject="
				+ subject + ", msg=" + msg + "]";
	}
	
	
	
}
