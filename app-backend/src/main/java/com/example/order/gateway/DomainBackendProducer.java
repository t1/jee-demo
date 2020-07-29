package com.example.order.gateway;

import java.net.URI;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

public class DomainBackendProducer {
    @Inject
    @ConfigProperty(name = "stage")
    String stage;

    @Produces
    DomainBackend produceOrderDomainService() {
        URI baseUri = ConfigProvider.getConfig().getOptionalValue(DomainBackend.class.getName() + "/mp-rest/url", URI.class)
                .orElseGet(() -> URI.create("http://domain-backend-" + stage + ":8080/domain-backend"));
        return RestClientBuilder.newBuilder()
                .baseUri(baseUri)
                .build(DomainBackend.class);
    }
}
