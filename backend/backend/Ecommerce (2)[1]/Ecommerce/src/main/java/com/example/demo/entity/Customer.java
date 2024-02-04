package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="Customer")
public class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Customer_Id")
    private long customerId;

    @NotBlank(message = "Customer name cannot be blank")
    @Size(min = 2, max = 20, message = "Customer name must be between 2 and 20 characters")
    @Column(name = "Customer_Name")
    private String customerName;

    @NotBlank(message = "Customer phone Number cannot be blank")
    @Pattern(regexp = "[0-9]+", message = "Customer phone Number must contain only digits")
    @Size(min = 10,  message = "Customer phone Number must be 10 Digits")
    @Column(name = "Customer_Phone")
    private String customerPhone;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 2, max = 20, message = "Username must be between 2 and 20 characters")
    @Column(name = "User_Name")
    private String username;

    @NotBlank(message = "User password cannot be blank")
    @Size(min = 4, message = "User password must be at least 4 characters")
    @Column(name = "User_Password")
    private String userpassword;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @Column(name = "email_Id")
    private String email;
    
    @NotBlank(message ="Address cannot be blank")
    @Size(min =4, message = "Customer Address must be at least 4 characters")
    @Column(name = "Address")
    private String address;
    
    public Customer() {
	}

	public Customer(long customerId, String customerName, String customerPhone,String address, String username, String userpassword,
			String email) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.username = username;
		this.userpassword = userpassword;
		this.email = email;
		this.address=address;
		
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
    
  
}
