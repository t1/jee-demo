package com.example.order;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @Builder
public class Order {
    private LocalDate orderDate;
    private Person customer;
    private List<OrderItem> items;
}
