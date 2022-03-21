package com.zah.ecommerce.service;

import com.zah.ecommerce.dto.Purchase;
import com.zah.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);


}
