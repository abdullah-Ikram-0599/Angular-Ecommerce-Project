package com.zah.ecommerce.dto;

import com.zah.ecommerce.entity.Address;
import com.zah.ecommerce.entity.Customer;
import com.zah.ecommerce.entity.Order;
import com.zah.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {


    private Customer customer;

    private Address shippingAddress;

    private Address billingAddress;

    private Order order;

    private Set<OrderItem> orderItems;






}
