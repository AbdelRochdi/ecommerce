package com.youcode.ecommerce.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	public List<Product> findAllProductsByCategory(int page, int limit, Long id) {
		
		if (page > 0)
			page -= 1;

		Pageable pageable = PageRequest.of(page, limit);

		Page<Product> productPage = productRepository.findByCategoryId(id, pageable);

		List<Product> products = productPage.getContent();
		
		return products;
	}
	
	@Override
	public List<Product> findAllProductsByKeyword(int page, int limit, String name) {
		
		if (page > 0)
			page -= 1;

		Pageable pageable = PageRequest.of(page, limit);

		Page<Product> productPage = productRepository.findByNameContaining(name, pageable);

		List<Product> products = productPage.getContent();
		
		return products;
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
