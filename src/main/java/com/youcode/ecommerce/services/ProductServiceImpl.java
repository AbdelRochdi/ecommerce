package com.youcode.ecommerce.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.youcode.ecommerce.entities.Product;
import com.youcode.ecommerce.repositories.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public Product update(Long id, Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product create(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
	}

}
