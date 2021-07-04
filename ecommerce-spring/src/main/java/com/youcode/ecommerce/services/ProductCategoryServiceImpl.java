package com.youcode.ecommerce.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.youcode.ecommerce.entities.ProductCategory;
import com.youcode.ecommerce.repositories.ProductCategoryRepository;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {

	private ProductCategoryRepository productCategoryRepository;

	public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
		this.productCategoryRepository = productCategoryRepository;
	}

	@Override
	public List<ProductCategory> findAll() {
		return productCategoryRepository.findAll();
	}

	@Override
	public Optional<ProductCategory> findById(Long id) {
		return productCategoryRepository.findById(id);
	}

	@Override
	public ProductCategory update(Long id, ProductCategory productCategory) {
		return productCategoryRepository.save(productCategory);
	}

	@Override
	public ProductCategory create(ProductCategory productCategory) {
		return productCategoryRepository.save(productCategory);
	}

	@Override
	public void delete(Long id) {
		productCategoryRepository.deleteById(id);
	}

}
