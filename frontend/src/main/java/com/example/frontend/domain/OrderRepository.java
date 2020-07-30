package com.example.frontend.domain;

public interface OrderRepository {
    Order getOrderById(String orderId);
}
