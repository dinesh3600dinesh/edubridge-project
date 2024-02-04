package com.example.demo.dao;

import com.example.demo.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

	List<Product> findByPnameIgnoreCase(String pname);
}
