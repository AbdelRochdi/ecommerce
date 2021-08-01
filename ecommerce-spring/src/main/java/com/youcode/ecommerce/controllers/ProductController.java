package com.youcode.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.youcode.ecommerce.entities.Product;
import com.youcode.ecommerce.services.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("")
	public ResponseEntity<List<Product>> findAll() {

		List<Product> productList = productService.findAll();

		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}
	
	@GetMapping("/searchByCategory")
	public ResponseEntity<List<Product>> getAllProductsByCategory(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "15") int limit, @RequestParam(value = "category") Long category) {

		List<Product> products = productService.findAllProductsByCategory(page, limit, category);

		return new ResponseEntity<>(products, HttpStatus.OK);

	}
	
	@GetMapping("/searchByName")
	public ResponseEntity<List<Product>> getAllProductsByKeyword(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "15") int limit, @RequestParam(value = "name") String name) {

		List<Product> products = productService.findAllProductsByKeyword(page, limit, name);

		return new ResponseEntity<>(products, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {

		Optional<Product> product = productService.findById(id);

		if (product.isPresent()) {
			return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
		} else {
			throw new EntityNotFoundException("The product with the id " + id + " doesn't exist.");
		}

	}

	@PostMapping("")
	public ResponseEntity<Product> create(@RequestBody Product product) {

		Product createdProduct = productService.create(product);

		return new ResponseEntity<Product>(createdProduct, HttpStatus.CREATED);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable Long id) {

		Product updatedProduct = productService.update(id, product);

		return new ResponseEntity<Product>(updatedProduct, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {

		productService.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
