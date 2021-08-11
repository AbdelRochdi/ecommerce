package com.youcode.ecommerce.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.youcode.ecommerce.entities.Product;
import com.youcode.ecommerce.entities.ProductList;
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
	public ProductList findAllProductsByCategory(int page, int limit, Long id) {

		Pageable pageable = PageRequest.of(page, limit);

		Page<Product> productPage = productRepository.findByCategoryId(id, pageable);
		
		ProductList productList = new ProductList();

		productList.setProductList(productPage.getContent());
		productList.setTotalElements(productPage.getTotalElements());
		productList.setTotalPages(productPage.getTotalPages());
		productList.setPageNumber(productPage.getNumber());
		productList.setPageSize(productPage.getSize());
		
		return productList;
	}
	
	@Override
	public ProductList findAllProductsByKeyword(int page, int limit, String name) {

		Pageable pageable = PageRequest.of(page, limit);

		Page<Product> productPage = productRepository.findByNameContaining(name, pageable);
		
		ProductList productList = new ProductList();

		productList.setProductList(productPage.getContent());
		productList.setTotalElements(productPage.getTotalElements());
		productList.setTotalPages(productPage.getTotalPages());
		productList.setPageNumber(productPage.getNumber());
		productList.setPageSize(productPage.getSize());
		
		return productList;
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
