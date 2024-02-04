package com.example.demo.service;

import com.example.demo.dao.ProductDao;
import com.example.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao dao;
	



	@Override
	public List<Product> findAll() {

		return dao.findAll();
	}

	@Override
	public Optional<Product> findByProductId(int id) {
		return this.dao.findById(id);
	}

	@Override
	public List<Product> findByPname(String pname) {
		return dao.findByPnameIgnoreCase(pname);
	}

	@Override
	public Product addProduct(Product product) {
		product.setImg("assets/images/"+product.getImg());
		return dao.save(product);
	}

	@Override
	public void deleteByProductId(int id) {
		dao.deleteById(id);
		
	}

	@Override
	public void updateProduct(Product product) {
		this.dao.save(product);
		
	}
	

   

    
//    public ProductServiceImpl(ProductDao productdao, OrderDao orderdao) {
//        this.orderdao = orderdao;
//    }
//
//   
//
//    public boolean hasOrders(long productId) {
//        return orderdao.existsById(productId);
//    }

//    @Transactional
//    public void deleteOrdersByProductId(long productId) {
//        orderdao.deleteById(productId);
//    }

	
}

