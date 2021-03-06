package com.youcode.ecommerce.services;

import java.util.List;
import java.util.Optional;

import com.youcode.ecommerce.entities.Product;
import com.youcode.ecommerce.entities.ProductList;

public interface ProductService {
	
	List<Product> findAll();
	
	ProductList findAllProductsByCategory(int page, int limit, Long id);
	
	ProductList findAllProductsByKeyword(int page, int limit, String name);
	
	Optional<Product> findById(Long id);
	
	Product update(Long id, Product product);
	
	Product create(Product product);
	
	void delete(Long id);

}
