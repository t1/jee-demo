package com.example.order.gateway;

import static javax.ws.rs.core.Response.Status.BAD_GATEWAY;
import static javax.ws.rs.core.Response.Status.Family.SERVER_ERROR;

import javax.inject.Inject;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.WebApplicationException;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.example.order.domain.Order;
import com.example.order.domain.OrderRepository;

public class OrderGateway implements OrderRepository {
    @Inject @RestClient
    OrderDomainService service;

    @Override public Order getOrderById(String orderId) {
        try {
            return service.getOrderById(orderId);
        } catch (WebApplicationException e) {
            if (e.getResponse().getStatusInfo().getFamily().equals(SERVER_ERROR))
                throw new ServerErrorException("failed call to order-domain service", BAD_GATEWAY, e);
            throw e;
        }
    }
}
