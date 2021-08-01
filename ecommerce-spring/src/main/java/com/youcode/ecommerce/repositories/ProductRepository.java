package com.youcode.ecommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.youcode.ecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Page<Product> findByCategoryId(Long id, Pageable pageable);
	
	Page<Product> findByNameContaining(String name, Pageable pageable);

	
}
