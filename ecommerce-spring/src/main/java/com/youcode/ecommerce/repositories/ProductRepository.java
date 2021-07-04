package com.youcode.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youcode.ecommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
