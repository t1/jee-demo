package test;

import java.time.LocalDate;

import com.example.frontend.domain.Order;
import com.example.frontend.domain.OrderItem;
import com.example.frontend.domain.Person;

public class TestData {
    public static final Order ORDER = Order.builder()
            .id("1")
            .orderDate(LocalDate.now())
            .customer(Person.builder()
                    .id("1")
                    .name("Jane Doe")
                    .build())
            .item(OrderItem.builder()
                    .id("1")
                    .count(3)
                    .product("Foobar")
                    .pieceCostInCent(1234)
                    .build())
            .item(OrderItem.builder()
                    .id("2")
                    .count(2)
                    .product("Bazzing")
                    .pieceCostInCent(5678)
                    .build())
            .build();
}
