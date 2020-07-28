package com.example.orderdomain.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter @Setter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class Order {
    private String id;
    private LocalDate orderDate;
    private Person customer;
    private @Singular List<OrderItem> items;
}
