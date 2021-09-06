package com.youcode.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youcode.ecommerce.dto.Purchase;
import com.youcode.ecommerce.dto.PurchaseResponse;
import com.youcode.ecommerce.services.CheckoutService;

@RestController
@CrossOrigin
@RequestMapping("api/checkout/")
public class CheckoutController {
	
	@Autowired
	private CheckoutService checkoutService;

	@PostMapping("purchase")
	public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
		PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
		
		return purchaseResponse;
	}
}
