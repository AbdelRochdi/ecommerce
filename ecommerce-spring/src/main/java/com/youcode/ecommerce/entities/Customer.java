package com.youcode.ecommerce.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer extends UserEntity{
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<Order> orders;
	
	public void add(Order order) {
		if (order != null) {
			if (orders == null) {
				orders = new HashSet<Order>();
			}

			orders.add(order);
			order.setCustomer(this);
		}
	}

	public Customer() {
		super();
	}
	

	public Customer(Set<Order> orders) {
		super();
		this.orders = orders;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	


	
}
