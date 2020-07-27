package com.example.order.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class Order {
    private String id;
    private LocalDate orderDate;
    private Person customer;
    private List<OrderItem> items;
}
