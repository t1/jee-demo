package com.example.orderdomain.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.example.orderdomain.domain.Order;
import com.example.orderdomain.domain.OrderRepository;

public class DbOrderRepository implements OrderRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override public Order getOrderById(long orderId) {
        Order order = entityManager.find(Order.class, orderId);
        if (order == null)
            throw new NoResultException("no order with id " + orderId);
        return order;
    }
}
