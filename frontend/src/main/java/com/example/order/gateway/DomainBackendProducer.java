package com.example.order.gateway;

import java.net.URI;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DomainBackendProducer {
    @Inject
    @ConfigProperty(name = "stage")
    String stage;

    @Produces
    DomainBackend produceOrderDomainService() {
        URI baseUri = ConfigProvider.getConfig().getOptionalValue(MP_REST_CLIENT_CONFIG_KEY, URI.class)
                .orElseGet(() -> URI.create("http://backend-" + stage + ":8080/backend"));
        log.info("produce DomainBackend rest client for {}", baseUri);
        return RestClientBuilder.newBuilder()
                .baseUri(baseUri)
                .build(DomainBackend.class);
    }

    public static final String MP_REST_CLIENT_CONFIG_KEY = DomainBackend.class.getName() + "/mp-rest/url";
}
