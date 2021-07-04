package com.youcode.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youcode.ecommerce.entities.ProductCategory;
import com.youcode.ecommerce.services.ProductCategoryService;

@RestController
@RequestMapping("/api/product-categories")
public class ProductCategoryController {
	
	private ProductCategoryService productCategoryService;

	public ProductCategoryController(ProductCategoryService productCategoryService) {
		this.productCategoryService = productCategoryService;
	}

	@GetMapping("")
	public ResponseEntity<List<ProductCategory>> findAll() {

		List<ProductCategory> productCategoryList = productCategoryService.findAll();

		return new ResponseEntity<List<ProductCategory>>(productCategoryList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductCategory> findById(@PathVariable Long id) {

		Optional<ProductCategory> productCategory = productCategoryService.findById(id);

		if (productCategory.isPresent()) {
			return new ResponseEntity<ProductCategory>(productCategory.get(), HttpStatus.OK);
		} else {
			throw new EntityNotFoundException("The product category with the id " + id + " doesn't exist.");
		}

	}

	@PostMapping("")
	public ResponseEntity<ProductCategory> create(@RequestBody ProductCategory productCategory) {

		ProductCategory createdProductCategory = productCategoryService.create(productCategory);

		return new ResponseEntity<ProductCategory>(createdProductCategory, HttpStatus.CREATED);

	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductCategory> update(@RequestBody ProductCategory productCategory, @PathVariable Long id) {
		
		ProductCategory updatedProductCategory = productCategoryService.update(id, productCategory);

		return new ResponseEntity<ProductCategory>(updatedProductCategory, HttpStatus.ACCEPTED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
		
		productCategoryService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}


}
