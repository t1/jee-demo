package com.example.orderdomain.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Entity(name = "ORDERS")
@Getter @Setter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class Order {
    @Id private long id;
    private LocalDate orderDate;
    @ManyToOne
    private Person customer;
    @OneToMany
    private @Singular List<OrderItem> items;
}
