package com.example.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class OrderItem {
    private String id;
    private int count;
    private String product;
    private int pieceCostInCent;
}
