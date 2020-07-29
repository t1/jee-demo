package com.example.order.boundary;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import io.opentracing.Tracer;

@Provider
public class RestInterceptor implements ContainerRequestFilter, ContainerResponseFilter {

	@Inject
	Tracer tracer;

	@Context
	ResourceInfo resourceInfo;

	public RestInterceptor() {

	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("User: ").append(requestContext.getSecurityContext().getUserPrincipal() == null ? "unknown"
				: requestContext.getSecurityContext().getUserPrincipal());
		sb.append(" - Path: ").append(requestContext.getUriInfo().getPath());
		sb.append(" - Header: ").append(requestContext.getHeaders());
		sb.append(" - Entity: ").append(getEntityBody(requestContext));
		tracer.activeSpan().log(sb.toString());
	}

	private String getEntityBody(ContainerRequestContext requestContext) {
		if (isJson(requestContext)) {
			try {
				return IOUtils.toString(requestContext.getEntityStream(), Charsets.UTF_8);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
		return null;
	}


	boolean isJson(ContainerRequestContext request) {
		return request.getMediaType().toString().contains("application/json");
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("Header: ").append(responseContext.getHeaders());
		sb.append(" - Entity: ").append(responseContext.getEntity());
		tracer.activeSpan().log(sb.toString());
	}

}
