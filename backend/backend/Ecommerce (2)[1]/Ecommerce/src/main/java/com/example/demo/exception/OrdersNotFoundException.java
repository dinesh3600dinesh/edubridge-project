package com.example.demo.exception;

public class OrdersNotFoundException extends RuntimeException {
	public OrdersNotFoundException(Long cusid) {
        super("Failed to retrieve orders for user with ID: " + cusid);
        System.out.println("Failed to retrieve orders for user with ID: " + cusid);
	}
}

