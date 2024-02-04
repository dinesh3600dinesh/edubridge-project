package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Payment;

public interface PaymentDao extends JpaRepository<Payment, Long> {
	public List<Payment> findByOrderId(long orderId); //  Admin
	List<Payment> findByCustomerCustomerId(long customerId);
}
