package com.example.orderdomain.domain;

public interface OrderRepository {
    Order getOrderById(String orderId);
}