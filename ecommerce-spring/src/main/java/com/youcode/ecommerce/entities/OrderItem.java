package com.youcode.ecommerce.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_item")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="unit_price")
	private BigDecimal unitPrice;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="product_id")
	private Long productId;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;

	public OrderItem() {
	}

	public OrderItem(Long id, String imageUrl, BigDecimal unitPrice, int quantity, Long productId) {
		this.id = id;
		this.imageUrl = imageUrl;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.productId = productId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
