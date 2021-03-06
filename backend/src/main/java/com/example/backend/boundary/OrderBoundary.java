package com.example.backend.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.Tag;
import org.eclipse.microprofile.metrics.annotation.Timed;

import com.example.backend.domain.Order;
import com.example.backend.domain.OrderRepository;

@Stateless
@Timed
@Boundary
@Path("/orders")
public class OrderBoundary {
    @Inject
    OrderRepository repository;

    @Inject
    MetricRegistry metrics;

    @GET @Path("/{orderId}")
    public Order getOrder(@PathParam("orderId") long orderId) {
        Tag tag = new Tag("orderId", Long.toString(orderId));
        metrics.counter(ORDER_ID_COUNTER, tag).inc();
        return repository.getOrderById(orderId);
    }

    private static final String ORDER_ID_COUNTER = MetricRegistry.name(OrderBoundary.class, "get-order");
}
