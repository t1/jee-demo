package com.example.orderdomain.boundary;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import javax.enterprise.inject.Stereotype;

import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.opentracing.Traced;

@Retention(RUNTIME)
@Stereotype
@Traced
public @interface Boundary {}