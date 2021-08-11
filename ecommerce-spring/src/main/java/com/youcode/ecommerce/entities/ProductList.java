package com.youcode.ecommerce.entities;

import java.util.List;

public class ProductList {

	private List<Product> productList;
	private int totalPages;
	private Long totalElements;
	private int pageSize;
	private int pageNumber;
		
	public ProductList() {
	}

	public ProductList(List<Product> productList, int totalPages, Long totalElements, int pageSize, int pageNumber) {
		this.productList = productList;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
}
