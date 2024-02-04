package com.example.demo.exception;

public class OrderNotCanceledException extends RuntimeException{
	
	 public OrderNotCanceledException() {
	        super("Order is not canceled");
	    }

	    public OrderNotCanceledException(String message) {
	        super(message);
	    }

	    public OrderNotCanceledException(String message, Throwable cause) {
	        super(message, cause);
	    }

}
