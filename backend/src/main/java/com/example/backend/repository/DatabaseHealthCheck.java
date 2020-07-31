package com.example.backend.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;

@ApplicationScoped
public class DatabaseHealthCheck {

    @PersistenceContext
    protected EntityManager em;

    @Produces
    @Liveness
    HealthCheck dbCheck() {
        Query query = em.createNativeQuery("SELECT 1");
        return () -> {
            HealthCheckResponseBuilder state = HealthCheckResponse.named("db");
            try {
                query.getSingleResult();
                state.up();
            } catch (RuntimeException e) {
                state.down().withData("exception", e.toString());
            }
            return state.build();
        };
    }
}
