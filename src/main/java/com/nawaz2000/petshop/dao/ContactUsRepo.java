package com.nawaz2000.petshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nawaz2000.petshop.entity.ContactUs;

@Repository("contactUsRepo")
public interface ContactUsRepo extends JpaRepository<ContactUs, Integer> {

}
