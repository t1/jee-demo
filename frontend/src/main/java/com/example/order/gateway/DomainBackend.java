package com.example.order.gateway;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.example.order.domain.Order;

@Path("/orders")
public interface DomainBackend {
    @GET @Path("/{orderId}")
    Order getOrderById(@PathParam("orderId") String orderId);
}
