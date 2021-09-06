package com.youcode.ecommerce.dto;

import java.util.Set;

import com.youcode.ecommerce.entities.Address;
import com.youcode.ecommerce.entities.Customer;
import com.youcode.ecommerce.entities.Order;
import com.youcode.ecommerce.entities.OrderItem;

public class Purchase {

	private Customer customer;
	private Address billingAddress;
	private Address shippingAddress;
	private Order order;
	private Set<OrderItem> orderItems;

	public Purchase() {
	}

	public Purchase(Customer customer, Address billingAddress, Address shippingAddress, Order order,
			Set<OrderItem> orderItems) {
		this.customer = customer;
		this.billingAddress = billingAddress;
		this.shippingAddress = shippingAddress;
		this.order = order;
		this.orderItems = orderItems;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}
