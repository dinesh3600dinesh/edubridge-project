package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CustomerDao;
import com.example.demo.exception.CustomerDataAlreadyFoundException;
import com.example.demo.exception.CustomerIdNotFoundException;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.service.CustomerService;
import com.example.demo.service.EmailService;
import com.example.demo.entity.Customer;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("customer")
public class CustomerController {
	
	@Autowired
	CustomerService service;
	
	@GetMapping("/viewcustomer")
	public ResponseEntity<List<Customer>> findAll() {
		return new ResponseEntity<List<Customer>>(this.service.findAll(), HttpStatus.OK);
	}
	
	@Autowired
	CustomerDao dao;
	 //Based on id
	@PutMapping("/update")
	public ResponseEntity<Map<String, String>> updateUser(@RequestBody Customer e) {
		try {
			if (this.dao.findById(e.getCustomerId()).isPresent()) {
				Customer existing = this.dao.findById(e.getCustomerId()).get();
				existing.setCustomerName(e.getCustomerName());
				existing.setCustomerPhone(e.getCustomerPhone());
				existing.setUsername(e.getUsername());
				existing.setUserpassword(e.getUserpassword());
				existing.setEmail(e.getEmail());
				existing.setAddress(e.getAddress());
				this.dao.save(existing);

				Map<String, String> response = new HashMap<String, String>();
				response.put("status", "Success");
				response.put("message", "Customer data updated!!");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.CREATED);
			} else {
				Map<String, String> response = new HashMap<String, String>();
				response.put("status", "Failed");
				response.put("message", "Customer data not found!!");
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e1) {
			Map<String, String> response = new HashMap<String, String>();
			response.put("status", "Failed");
			response.put("message", "Customer data not updated!!");
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/findid/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable long id) throws CustomerIdNotFoundException {
		Optional<Customer> customerOptional = dao.findById(id);

		if (customerOptional.isPresent()) {
			return ResponseEntity.ok(customerOptional.get());
		} else {
			throw new CustomerIdNotFoundException("Customer with Id " + id + " not found.");
		}
	}
	@GetMapping("/username/{username}")
	public ResponseEntity<Customer> getCustomerByUsername(@PathVariable String username) {
		Customer customer = service.getCustomerByUsername(username);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer with username " + username + " not found.");
		}
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	@PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Customer customerData) {
        System.out.println("Received a login request for admin: " + customerData.getUsername());

        Customer customer = service.findByUsername(customerData.getUsername());

        if (customer.getUserpassword().equals(customerData.getUserpassword())) {
            System.out.println("Admin login successful: " + customer);

            Customer sendcustomer = new Customer();
            sendcustomer.setCustomerId(customer.getCustomerId());
            sendcustomer.setCustomerName(customer.getCustomerName());
            sendcustomer.setCustomerPhone(customer.getCustomerPhone());
            sendcustomer.setAddress(customer.getAddress());
            sendcustomer.setUsername(customer.getUsername());
            sendcustomer.setUserpassword(customer.getUserpassword());
            sendcustomer.setEmail(customer.getEmail());
           

            return ResponseEntity.ok(sendcustomer);
        } else {
            System.out.println("Admin login failed for: " + customerData.getUsername());
            return (ResponseEntity<?>) ResponseEntity.internalServerError();
        }
    }
	
	@Autowired
    private EmailService emailService;
	
	@Autowired
	CustomerService customerService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signupAndSendEmail(@RequestBody Customer customer) {
        try {
            // Check if customer data already exists
            emailService.checkCustomerData(customer.getUsername());

         // If check passes, save the customer data
            customerService.saveOrUpdate(customer);

           
            // Send welcome email asynchronously
            emailService.sendWelcomeEmail(customer.getEmail(), customer.getUsername());

            // Return success response
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "User registered and welcome email sent!!");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (CustomerDataAlreadyFoundException e) {
            // Handle the case where customer data already exists
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "User registration failed. Customer data already exists.");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{cusid}")
	public ResponseEntity<Map<String, String>> deleteCustomer(@PathVariable(name = "id") int id) {
		System.out.println("Received a request to delete user data by ID: " + id);

		try {
			this.service.deleteBycusId(id);
			System.out.println("Customer data deleted successfully for ID: " + id);

			Map<String, String> response = new HashMap<>();
			response.put("status", "success");
			response.put("message", "User data deleted!!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (CustomerNotFoundException e) {

			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("status", "failed");
			errorResponse.put("message", "User not found with ID: " + id);
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		} catch (Exception ex) {

			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("status", "failed");
			errorResponse.put("message", "An error occurred.");
			return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}