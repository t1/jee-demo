package test.it;

import static com.example.order.gateway.DomainBackendProducer.MP_REST_CLIENT_CONFIG_KEY;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import javax.ws.rs.client.WebTarget;

import org.junit.jupiter.api.Disabled;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.github.t1.testcontainers.jee.JeeContainer;
import com.github.t1.testcontainers.jee.WildflyContainer;

// TODO we need to start and stub some mock-server for the backend IN the docker network
@Disabled
@Testcontainers
public class TestContainersIT extends AbstractIT {
    @Container static JeeContainer CONTAINER = new WildflyContainer("20.0.1.Final")
            .withEnv("stage", "test-container")
            .withEnv(MP_REST_CLIENT_CONFIG_KEY, "http://localhost:8081/backend")
            .withDeployment("target/frontend.war");

    @Override WebTarget target() {
        return CONTAINER.target();
    }

    // TODO expose management port in JeeTestContainers
    @Override WebTarget management() {
        assumeTrue(CONTAINER == null, "management port not yet exposed by wildfly");
        return null;
    }
}
