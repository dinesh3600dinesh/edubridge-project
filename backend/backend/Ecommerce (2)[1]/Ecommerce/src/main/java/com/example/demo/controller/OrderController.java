package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Order;
import com.example.demo.exception.OrdersNotFoundException;
import com.example.demo.service.OrderService;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("order")
public class OrderController {

	private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/allorders")
    public ResponseEntity<List<Order>> getAllOrders() {
        System.out.println("Received a request to fetch all orders.");
        
        List<Order> orders = orderService.getAllOrders();
        System.out.println("Returning all orders: " + orders);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("orderid/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) {
        System.out.println("Received a request to fetch order by ID: " + orderId);

        try {
			Order orders = orderService.getOrderById(orderId);
			if (orders==null) {
				return new ResponseEntity<>("No orders found with the specified order ID.", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (OrdersNotFoundException ex) {
			return new ResponseEntity<>("No orders found with the specified order ID.", HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>("An error occurred while retrieving orders.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @PostMapping("/create/{customerId}/{productid}")
    public ResponseEntity<Order> createOrder(@RequestBody Order order, @PathVariable long customerId,
                                             @PathVariable int productid) {
        System.out.println("Received a request to create an order for customer ID: " + customerId + " and product ID: " + productid);

        Order savedOrder = orderService.saveOrder(order, customerId, productid);
        System.out.println("Order created successfully: " + savedOrder);

        return ResponseEntity.ok(savedOrder);
    }
    
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        System.out.println("Received a request to delete order by ID: " + orderId);

        orderService.deleteOrder(orderId);
        System.out.println("Order deleted successfully for ID: " + orderId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getOrdersByCustomerId(@PathVariable long customerId) {
        System.out.println("Received a request to fetch orders by customer ID: " + customerId);

        try {
            List<Order> orders = orderService.findOrdersByCustomerId(customerId);
            if (orders.isEmpty()) {
                System.out.println("No orders found for customer ID: " + customerId);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            System.out.println("Returning orders for customer ID " + customerId + ": " + orders);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            System.out.println("An error occurred while fetching orders for customer ID " + customerId + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }
}