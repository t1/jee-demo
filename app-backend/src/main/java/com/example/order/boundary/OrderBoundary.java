package com.example.order.boundary;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.example.order.domain.Order;
import com.example.order.domain.OrderRepository;

@Path("/orders")
public class OrderBoundary {
    @Inject
    OrderRepository repository;

    @GET @Path("/{orderId}")
    public Order getOrder(@PathParam("orderId") String orderId) {
        return repository.getOrderById(orderId);
    }
}
