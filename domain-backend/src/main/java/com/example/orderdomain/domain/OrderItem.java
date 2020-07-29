package com.example.orderdomain.domain;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "order_item")
@Getter @Setter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class OrderItem {
    @Id private long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonbTransient // prevent recursion
    Order order;

    private int count;

    private String product;

    @Column(name = "piece_cost_in_cent")
    private int pieceCostInCent;
}
