package com.youcode.ecommerce.services;

import java.util.List;
import java.util.Optional;

import com.youcode.ecommerce.entities.ProductCategory;

public interface ProductCategoryService {

	List<ProductCategory> findAll();

	Optional<ProductCategory> findById(Long id);

	ProductCategory update(Long id, ProductCategory productCategory);

	ProductCategory create(ProductCategory productCategory);

	void delete(Long id);

}
