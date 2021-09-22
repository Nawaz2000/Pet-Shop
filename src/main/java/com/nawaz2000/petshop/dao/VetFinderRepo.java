package com.nawaz2000.petshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nawaz2000.petshop.entity.VetFinder;

@Repository("vetFinderRepo")
public interface VetFinderRepo extends JpaRepository<VetFinder, Integer> {

}
