package com.example.order.boundary;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@ApplicationScoped
class HealthChecks {

    @Inject
    @ConfigProperty
    String version;

    @Inject
    @ConfigProperty(name = "stage")
    String stage;

    @Produces
    @Liveness
    HealthCheck applicationInfoCheck() {
        return () -> HealthCheckResponse.named("frontend")
                .up()
                .withData("stage", stage)
                .withData("version", version)
                .build();
    }
}
