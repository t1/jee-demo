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

    @Produces
    @Liveness
        // @Readiness
    HealthCheck check1() {
        return () -> HealthCheckResponse.named("app-backend")
                .up()
                .withData("stage", System.getProperty("stage", "undefined"))
                .withData("version", version)
                .build();
    }
}
