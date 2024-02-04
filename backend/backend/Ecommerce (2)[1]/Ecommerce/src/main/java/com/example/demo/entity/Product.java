package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "productid")
	
	@Min(value = 1, message = "Product ID must be greater than or equal to 1")
	@Digits(integer = 10, fraction = 0, message = "Item ID should be a number")
	private int productid;
	@NotBlank(message = "Item name cannot be blank")
	@Column(name = "pname")
	private String pname;

	@NotNull(message = "Price cannot be null")
	@Positive(message = "Price must be a positive value")
	private Double price;

	@NotBlank(message = "Image URL cannot be blank")
	@Column(name = "img")
	private String img;
	
	
	public Product() {

	}


	public Product(
			@Min(value = 1, message = "Product ID must be greater than or equal to 1") @Digits(integer = 10, fraction = 0, message = "Item ID should be a number") int productid,
			@NotBlank(message = "Item name cannot be blank") String pname,
			@NotNull(message = "Price cannot be null") @Positive(message = "Price must be a positive value") Double price,
			@NotBlank(message = "Image URL cannot be blank") String img) {
		super();
		this.productid = productid;
		this.pname = pname;
		this.price = price;
		this.img = img;
	}


	public int getProductid() {
		return productid;
	}


	public void setProductid(int productid) {
		this.productid = productid;
	}


	public String getPname() {
		return pname;
	}


	public void setPname(String pname) {
		this.pname = pname;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	@Override
	public String toString() {
		return "Product [productid=" + productid + ", pname=" + pname + ", price=" + price + ", img=" + img + "]";
	}

	
}

	