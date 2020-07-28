package com.example.order.gateway;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.example.order.domain.Order;

@RegisterRestClient
@Path("/orders")
public interface OrderDomainService {
    @GET @Path("/{orderId}")
    Order getOrderById(@PathParam("orderId") String orderId);
}
