package com.example.demo.service;



import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrderDao;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.exception.CustomerNotFoundException;





@Service
public class OrderServiceImpl implements OrderService{
	
	private final OrderDao orderdao;

	
	public OrderServiceImpl(OrderDao orderdao) {
		this.orderdao = orderdao;
	}

	@Autowired
	CustomerService customerservice;
	@Autowired
	ProductService productservice;

	@Override
	public List<Order> getAllOrders() {
		return orderdao.findAll();
	}

	@Override
	public Order getOrderById(Long orderId) {
		return orderdao.findById(orderId).orElse(null);
	}

	@Override
	public void deleteOrder(Long orderId) {
		orderdao.deleteById(orderId);

	}

	@Override
	public Order saveOrder(Order order, long customerId, int productid) {
		// Check if customer exists
		Customer customer = customerservice.findCustomerById(customerId).orElse(null);
		if (customer == null) {
			throw new CustomerNotFoundException("Invalid customer ID");
		}

		// Check if product exists
		Product product = productservice.findByProductId(productid).orElse(null);
		if (product == null) {
			throw new IllegalArgumentException("Invalid Product ID");
		}

		order.setOrderDate(new Date()); // Set the current date as the order date
		order.setOrderTime(new Date()); // Set the current time as the order time
		// Calculate total price
		order.calculateTotalPrice();
		// Set initial order status
		order.setStatus("Not Paid");

		// Set customer and menu details
		order.setCustomer(customer);
		order.setProduct(product);

		// Set total price in the entity to be saved in the database
		order.setTotalPrice(order.getTotalPrice());

		// Save the order
		return orderdao.save(order);
	}

	@Override
	public List<Order> findOrdersByCustomerId(long customerId) {
		return orderdao.findByCustomerCustomerId(customerId);
	}

}
	