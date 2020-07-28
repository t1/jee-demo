package com.example.orderdomain.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.orderdomain.domain.Order;
import com.example.orderdomain.domain.OrderRepository;

public class DbOrderRepository implements OrderRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override public Order getOrderById(String orderId) {
        return entityManager.find(Order.class, orderId);
    }
}
