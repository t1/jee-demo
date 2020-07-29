package com.example.order.gateway;

import java.net.URI;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

public class OrderServiceProducer {
    @Inject
    @ConfigProperty(name = "stage")
    String stage;

    @Produces
    OrderDomainService produceOrderDomainService() {
        return RestClientBuilder.newBuilder()
                .baseUri(URI.create("http://domain-backend-" + stage + ":8080/domain-backend"))
                .build(OrderDomainService.class);
    }
}
