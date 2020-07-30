package com.example.backend.domain;

public interface OrderRepository {
    Order getOrderById(long orderId);
}
