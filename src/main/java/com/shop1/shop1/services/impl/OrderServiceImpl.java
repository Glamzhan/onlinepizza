package com.shop1.shop1.services.impl;

import com.shop1.shop1.entities.Order;
import com.shop1.shop1.repositories.OrderRepository;
import com.shop1.shop1.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Override
    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOrderById(long id) {

        return orderRepository.findOne(id);
    }
}
