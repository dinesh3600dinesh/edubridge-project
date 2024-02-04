package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Customer;


public interface CustomerService {
	
	public List<Customer>findAll();
	public void saveOrUpdate(Customer c);
	public  Optional<Customer> findCustomerById(long id);
	Customer findByUsername(String username);
	public Customer getCustomerByUsername(String username);
	public Optional<Customer> getUserByName(String username);
	public void checkCustomerData(String username, String userpassword);
	public void deleteBycusId(long id);

}
