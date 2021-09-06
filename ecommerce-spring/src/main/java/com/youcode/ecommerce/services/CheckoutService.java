package com.youcode.ecommerce.services;

import com.youcode.ecommerce.dto.Purchase;
import com.youcode.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

	PurchaseResponse placeOrder(Purchase purchase);
	
}
