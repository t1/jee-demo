package com.example.frontend.gateway;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.example.frontend.domain.Order;

@Path("/orders")
public interface DomainBackend {
    @GET @Path("/{orderId}")
    Order getOrderById(@PathParam("orderId") String orderId);
}
