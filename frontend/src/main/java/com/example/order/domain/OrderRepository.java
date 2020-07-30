package com.example.order.domain;

public interface OrderRepository {
    Order getOrderById(String orderId);
}
