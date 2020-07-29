package com.example.order.boundary;

import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

import java.security.Principal;
import java.util.Scanner;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import io.opentracing.Tracer;

@Provider
public class RestInterceptor implements ContainerRequestFilter, ContainerResponseFilter {

    @Inject
    Tracer tracer;

    @Context
    ResourceInfo resourceInfo;


    @Override
    public void filter(ContainerRequestContext requestContext) {
        tracer.activeSpan().log(""
                + "User: " + userName(requestContext)
                + " - Path: " + requestContext.getUriInfo().getPath()
                + " - Header: " + requestContext.getHeaders()
                + " - Entity: " + getEntityBody(requestContext));
    }

    private String userName(ContainerRequestContext requestContext) {
        Principal principal = requestContext.getSecurityContext().getUserPrincipal();
        return (principal == null) ? "unknown" : principal.getName();
    }
    private String getEntityBody(ContainerRequestContext requestContext) {
        if (isJson(requestContext)) {
            try (Scanner scanner = new Scanner(requestContext.getEntityStream(), UTF_8).useDelimiter("\\Z")) {
                return scanner.next();
            }
        }
        return null;
    }

    boolean isJson(ContainerRequestContext request) {
        return request.getMediaType().isCompatible(APPLICATION_JSON_TYPE);
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        tracer.activeSpan().log(""
                + "Header: " + responseContext.getHeaders()
                + " - Entity: " + responseContext.getEntity());
    }
}
