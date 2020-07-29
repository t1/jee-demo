package com.example.order.boundary;

import java.util.Random;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import com.example.order.domain.Order;
import com.example.order.domain.OrderRepository;

@Stateless
@Boundary
@Path("/")
public class OrderBoundary {
	@Inject
	OrderRepository repository;

	String cachedData;
	private int calls;
	private int breakCalls;
	@PostConstruct
	public void init() {
		cachedData = UUID.randomUUID().toString();
	}

	@GET
	@Path("orders/{orderId}")
	public Order getOrder(@PathParam("orderId") String orderId) throws InterruptedException {
		return repository.getOrderById(orderId);
	}

	public String sorry() {
		return "sorry this is a fallback";
	}

	@GET
	@Path("timeout")
	@Timeout(250)
	public String getTiemout() throws InterruptedException {
		randomSleep(400);

		return "Random string " + UUID.randomUUID().toString();
	}

	@GET
	@Path("timeoutRetry")
	@Timeout(250)
	@Retry(maxRetries = 2)
	public String getTiemoutRetry() throws InterruptedException {
		calls++;
		randomSleep(600);

		return "Random string " + UUID.randomUUID().toString() + " method calls: " + calls;
	}

	@GET
	@Path("timeoutFallback")
	@Timeout(250)
	@Fallback(fallbackMethod = "getCachedData")
	public String getTiemoutFallback() throws InterruptedException {
		randomSleep(400);

		return "Random string " + UUID.randomUUID().toString();
	}

	@GET
	@Path("circuitBreak")
	@Timeout(250)
	@Retry(maxRetries = 3)
	@CircuitBreaker(successThreshold = 10,
	requestVolumeThreshold = 4,
	failureRatio = 0.75,
	delay = 30000)
	public String breakCircuit() {
		breakCalls++;
		randomSleep(1000);
		return "Random string " + UUID.randomUUID().toString() + " method calls: " + breakCalls;
	}

	private void randomSleep(int sleep) {
		try {
			Thread.sleep(new Random().nextInt(sleep));
		} catch (Exception e) {
			System.out.println("The application is taking too long...");

		}
	}

	public String getCachedData() {

		return "Random (cached) String is " + cachedData;
	}
}
