package com.zah.ecommerce.service;

import com.zah.ecommerce.dao.CustomerRepository;
import com.zah.ecommerce.dto.Purchase;
import com.zah.ecommerce.dto.PurchaseResponse;
import com.zah.ecommerce.entity.Customer;
import com.zah.ecommerce.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zah.ecommerce.entity.Order;
//import javax.persistence.criteria.Order;
import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from dtos

        Order order = (Order) purchase.getOrder();



        // generate tracking number

        String orderTrackingNumber= generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);
        // populate order with OrderItems

        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));


        // populate order with  billingAddress and shippingAddress

        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order



        Customer customer = purchase.getCustomer();
        customer.add(order);

        // save to the database

        customerRepository.save(customer);

        // return a response

        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version = 4)
        return UUID.randomUUID().toString();
    }
}
