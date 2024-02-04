package com.example.demo.service;

import com.example.demo.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

	public List<Product> findAll();
	public Optional<Product> findByProductId(int id);
	List<Product> findByPname(String pname);
	public Product addProduct(Product product);
	public void deleteByProductId(int id);
	public void updateProduct(Product product);
	
//	boolean hasOrders(long productId);
//
//    void deleteOrdersByProductId(long productId);
}
