package com.example.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class OrderItem {
    private int count;
    private String product;
    private int pieceCostInCent;
}
