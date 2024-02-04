package com.example.demo.exception;

public class OrderAlreadyCanceledException  extends RuntimeException {
	public OrderAlreadyCanceledException(String message) {
        super(message);
    }

}
