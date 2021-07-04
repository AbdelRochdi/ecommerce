package com.youcode.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youcode.ecommerce.entities.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
