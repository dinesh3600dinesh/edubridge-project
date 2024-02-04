package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrderDao;
import com.example.demo.dao.PaymentDao;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.Payment;
@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentDao paymentdao;
	
	@Autowired
	private OrderDao orderdao;
	
	@Autowired
	private CustomerService customerService;
	
	public PaymentServiceImpl(PaymentDao paymentdao,
			CustomerService customerService) {
		super();
		this.paymentdao = paymentdao;
		
		this.customerService = customerService;
	}
	@Override
	public Payment addPayment(Payment payment, long orderId, long customerId) {
		Order order = orderdao.getById(orderId);
		payment.setOrderId(orderId);
		payment.setTotalPrice(order.getTotalPrice());
		payment.setPaidDate(LocalDate.now());
		payment.setPaidAmount(order.getTotalPrice());
		if (payment.getTotalPrice() == payment.getPaidAmount()) {
			order.setStatus("Paid");
		} else {

			order.setStatus("Not Paid");
		}
		Customer customer = customerService.findCustomerById(customerId).orElse(null);
		payment.setCustomer(customer);
    	return paymentdao.save(payment);
	}

	@Override
	public List<Payment> getAllPayments() {
		return paymentdao.findAll();
	}


	@Override
	public void deletePayment(long paymentId) {
		paymentdao.findById(paymentId);		
		if (paymentdao.existsById(paymentId)) {
		    paymentdao.deleteById(paymentId);
		}
	}


	@Override
	public Optional<Payment> findPaymentById(long id) {
		return this.paymentdao.findById(id);
	}
	@Override
	public List<Payment> getPaymentsByOrderId(long orderId) {
		return paymentdao.findByOrderId(orderId);
	}
	@Override
	public void deletePaymentsByOrderId(long orderId) {
		paymentdao.findByOrderId(orderId).forEach(payment -> paymentdao.delete(payment));
		
	}

	@Override
	public List<Payment> findPaymentsByCustomerId(long customerId) {
		return paymentdao.findByCustomerCustomerId(customerId);
	}


}
