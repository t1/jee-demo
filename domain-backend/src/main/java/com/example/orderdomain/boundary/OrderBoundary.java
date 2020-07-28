package com.example.orderdomain.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.example.orderdomain.domain.Order;
import com.example.orderdomain.domain.OrderRepository;

@Stateless
@Boundary
@Path("/orders")
public class OrderBoundary {
    @Inject
    OrderRepository repository;

    @GET @Path("/{orderId}")
    public Order getOrder(@PathParam("orderId") long orderId) {
        return repository.getOrderById(orderId);
    }
}
