package com.example.demo.controller;

import com.example.demo.dao.ProductDao;
import com.example.demo.entity.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("products")
public class ProductController {
	 @Autowired
	    ProductService service;
	    @Autowired
	    ProductDao dao;

	    @GetMapping("/product")
	    public ResponseEntity<List<Product>> findAll() {
	        System.out.println("Received a request to get all the Products.");

	        List<Product> products = this.service.findAll();

	        System.out.println("All products retrieved successfully. " + products);

	        return new ResponseEntity<>(products, HttpStatus.OK);
	    }

	    @GetMapping("/product/{productid}")
	    public ResponseEntity<?> getProductById(@PathVariable int productid) {
	        System.out.println("Received a request to get a product by ID: " + productid);

	        Optional<Product> product = service.findByProductId(productid);

	        if (product.isPresent()) {
	            System.out.println("Fetching Product By Id: " + product.get());
	            return new ResponseEntity<>(product.get(), HttpStatus.OK);
	        } else {
	        	Map<String, String> errorResponse = new HashMap<>();
				errorResponse.put("status", "failed");
				errorResponse.put("message", "Product not found for ID: " + productid);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	        }
	    }

	    @GetMapping("/productname/{pname}")
	    public ResponseEntity<?> getProductByPname(@PathVariable String pname) {
	        System.out.println("Received a request to get product by pname: " + pname);

	        List<Product> product = service.findByPname(pname);

	        if (product != null) {
				System.out.println("Product retrieved successfully.");
				return ResponseEntity.ok(product);
			} else {
				Map<String, String> errorResponse = new HashMap<>();
				errorResponse.put("status", "failed");
				errorResponse.put("message", "Product not found for name: " + pname);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
			}
	    }

	    @PostMapping("/addproduct")
	    public ResponseEntity<?> addproduct(@RequestBody Product product) {
	        System.out.println("Received a request to add a product: " + product);

	        try {
	            Product addproduct = service.addProduct(product);
	            System.out.println("New Product Added: " + addproduct);
	            return new ResponseEntity<>(addproduct, HttpStatus.CREATED);
	        } catch (Exception ex) {
				Map<String, String> errorResponse = new HashMap<>();
				errorResponse.put("status", "failed");
				errorResponse.put("message", "Failed to add the product.");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	    }
	    }

	    @PutMapping("/update")
	    public ResponseEntity<Map<String, String>> updateMenu(@RequestBody Product e) {
	        System.out.println("Received a request to update product: " + e);

	        try {
	            if (this.dao.findById(e.getProductid()).isPresent()) {
	                Product existingproduct = this.dao.findById(e.getProductid()).get();
	                existingproduct.setPname(e.getPname());
	                existingproduct.setPrice(e.getPrice());
	                existingproduct.setImg(e.getImg());
	                this.dao.save(existingproduct);

	                Map<String, String> response = new HashMap<>();
	                response.put("status", "success");
	                response.put("message", "Product updated!!");
	                System.out.println("Prduct updated successfully: " + existingproduct);
	                return new ResponseEntity<>(response, HttpStatus.CREATED);
	            } else {
	                Map<String, String> response = new HashMap<>();
	                response.put("status", "failed");
	                response.put("message", "Product not found!!");
	                System.out.println("Product not found for updating: " + e.getProductid());
	                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	            }
	        } catch (Exception e1) {
	            Map<String, String> response = new HashMap<>();
	            response.put("status", "failed");
	            response.put("message", "Product not updated!!");
	            System.out.println("Failed to update Product: " + e1.getMessage());
	            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	        }
	    }
	    //This method foreign constrain so we can't delete the method without deleting order table
	    @DeleteMapping("/delete/{productid}")
	    public ResponseEntity<Void> deleteByItem(@PathVariable int productid) {
	        System.out.println("Received a request to delete Product by ID: " + productid);

	        try {
	            service.deleteByProductId(productid);
	            System.out.println("Product deleted successfully for ID: " + productid);
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } catch (EmptyResultDataAccessException ex) {
	            System.out.println("Product not found for deletion, ID: " + productid);
	            throw new ProductNotFoundException("Product with ID " + productid + " not found.");
	        }
	    }
	    

}