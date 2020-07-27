package com.example.order.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class OrderItem {
    private String id;
    private int count;
    private String product;
    private int pieceCostInCent;
}
