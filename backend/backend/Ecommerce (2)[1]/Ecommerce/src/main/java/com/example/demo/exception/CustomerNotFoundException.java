package com.example.demo.exception;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(String message) {
        super(message);
    }
	public CustomerNotFoundException(long userId) {
        super("User not found with ID: " + userId);
        System.out.println("User not found with ID: " + userId);
    }


}
