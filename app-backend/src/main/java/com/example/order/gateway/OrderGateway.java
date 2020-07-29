package com.example.order.gateway;

import static javax.ws.rs.core.Response.Status.BAD_GATEWAY;
import static javax.ws.rs.core.Response.Status.Family.SERVER_ERROR;

import javax.inject.Inject;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.WebApplicationException;

import io.opentracing.Span;
import io.opentracing.Tracer;

import com.example.order.domain.Order;
import com.example.order.domain.OrderRepository;

public class OrderGateway implements OrderRepository {
	@Inject
	OrderDomainService service;

	@Inject
	Tracer tracer;

	@Override
	public Order getOrderById(String orderId) {
		try {
			Order order = service.getOrderById(orderId);

			Span span = tracer.activeSpan();
			span.setTag("orderId", orderId);
			span.log("parameters: " + orderId);
			span.log("response: " + order.toString());

			return order;
		} catch (WebApplicationException e) {
			if (e.getResponse().getStatusInfo().getFamily().equals(SERVER_ERROR))
				throw new ServerErrorException("failed call to order-domain service", BAD_GATEWAY, e);
			throw e;
		}
	}
}
