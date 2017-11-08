package com.shop1.shop1.services;

import com.shop1.shop1.entities.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);

    List<Order> findAll();

    Order findOrderById(long id);
}
