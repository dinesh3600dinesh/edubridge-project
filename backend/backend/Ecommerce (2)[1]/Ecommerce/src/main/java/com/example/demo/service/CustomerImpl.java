package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerDao;
import com.example.demo.exception.CustomerDataAlreadyFoundException;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.entity.Customer;
@Service
public class CustomerImpl implements CustomerService{
	@Autowired
	CustomerDao dao;
	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	@Override
	public void saveOrUpdate(Customer c) {
		// TODO Auto-generated method stub
		dao.save(c);
	}
	@Override
	public Optional<Customer> findCustomerById(long id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}
	@Override
	public Customer findByUsername(String username) {
		// TODO Auto-generated method stub
		return dao.findByUsername(username);
	}
	@Override
	public Customer getCustomerByUsername(String username) {
		// TODO Auto-generated method stub
		return dao.findByUsername(username);
	}
	@Override
	public Optional<Customer> getUserByName(String username) {
		// TODO Auto-generated method stub
		return this.dao.findByusername(username);
	}
	 @Override
	    public void checkCustomerData(String username, String userpassword) {
	        // Implement your checks here
	        // For example, check if the username already exists
	        if (dao.existsByUsername(username)) {
	            throw new CustomerDataAlreadyFoundException("Customer with username " + username + " already exists.");
	        }

}
	 @Override
		public void deleteBycusId(long id) {
			if (!dao.existsById(id)) {
				throw new CustomerNotFoundException(id);
			}
			dao.deleteById(id);
		}
}
