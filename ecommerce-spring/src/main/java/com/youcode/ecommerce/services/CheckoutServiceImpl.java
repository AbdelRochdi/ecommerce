package com.youcode.ecommerce.services;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youcode.ecommerce.dto.Purchase;
import com.youcode.ecommerce.dto.PurchaseResponse;
import com.youcode.ecommerce.entities.Customer;
import com.youcode.ecommerce.entities.Order;
import com.youcode.ecommerce.entities.OrderItem;
import com.youcode.ecommerce.repositories.CustomerRepository;

@Service
public class CheckoutServiceImpl implements CheckoutService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public PurchaseResponse placeOrder(Purchase purchase) {
		
		Order order = purchase.getOrder();
		
		String orderTrackingNumber = generateOrderTrackingNumber();
		
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());
		
		Customer customer = purchase.getCustomer();
		customer.add(order);
		
		customerRepository.save(customer);
		
		return new PurchaseResponse(orderTrackingNumber);
	}

	private String generateOrderTrackingNumber() {
		return UUID.randomUUID().toString();
	}

}
