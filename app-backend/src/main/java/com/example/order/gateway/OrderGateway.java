package com.example.order.gateway;

import com.example.order.domain.Order;
import com.example.order.domain.OrderItem;
import com.example.order.domain.OrderRepository;
import com.example.order.domain.Person;

public class OrderGateway implements OrderRepository {
    @Override public Order getOrderById(String orderId) {
        return Order.builder()
                .id(orderId)
                .customer(Person.builder().name("Jane Doe").build())
                .item(OrderItem.builder().count(3).product("Lord Of The Rings").pieceCostInCent(1234).build())
                .item(OrderItem.builder().count(1).product("The Hobbit").pieceCostInCent(1150).build())
                .build();
    }
}
