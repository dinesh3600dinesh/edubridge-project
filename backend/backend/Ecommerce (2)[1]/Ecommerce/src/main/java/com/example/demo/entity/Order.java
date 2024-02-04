package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.*	;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "order_id", unique = true)
	    private Long orderId;

	    @ManyToOne(targetEntity = Customer.class, cascade = { CascadeType.MERGE}, fetch = FetchType.EAGER)
	    @JoinColumn(name = "customer_Id", referencedColumnName = "customer_Id")
	    private Customer customer;

	    @Column(name = "order_date")
	    @Temporal(TemporalType.DATE)
	    private Date orderDate;

	    @Column(name = "order_time")
	    @Temporal(TemporalType.TIME)
	    private Date orderTime;

	    @ManyToOne(targetEntity = Product.class, cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	    @JoinColumn(name = "productid", referencedColumnName = "productid")
	    private Product product;

	    @Column(name = "quantity")
	    private int quantity;

	    @Column(name = "total_price")
	    private double totalPrice;

	    @NotBlank(message = "Order status cannot be blank")
	    @Column(name = "status")
	    private String status;



	public Order() {
	}

	

	public Order(Long orderId, Customer customer, Date orderDate, Date orderTime, Product product, int quantity,
			double totalPrice, @NotBlank(message = "Order status cannot be blank") String status) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.product = product;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.status = status;
	}



	

	public Long getOrderId() {
		return orderId;
	}



	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}



	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public Date getOrderDate() {
		return orderDate;
	}



	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}



	public Date getOrderTime() {
		return orderTime;
	}



	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
		calculateTotalPrice();
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
		calculateTotalPrice();
	}



	public double getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	// Calculate total price based on Product price and quantity
	public void calculateTotalPrice() {
		if (product != null && quantity > 0) {
			totalPrice = product.getPrice() * quantity;
		} else {
			totalPrice = 0.0; 
		}
	}

	

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customer=" + customer + ", orderDate=" + orderDate + ", orderTime="
				+ orderTime + ", product=" + product + ", quantity=" + quantity + ", totalPrice=" + totalPrice
				+ ", status=" + status + "]";
	}

	
}
