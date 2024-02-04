package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Customer;
@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
	
	Customer findByUsername(String username);
	 boolean existsByUsername(String username);
	@Query(value = "SELECT * FROM CUSTOMER u WHERE u.user_name = ?", nativeQuery = true)
	public Optional<Customer> findByusername(String username);

}
