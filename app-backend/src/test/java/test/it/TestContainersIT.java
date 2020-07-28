package test.it;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import javax.ws.rs.client.WebTarget;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.github.t1.testcontainers.jee.JeeContainer;
import com.github.t1.testcontainers.jee.WildflyContainer;

@Testcontainers
public class TestContainersIT extends AbstractIT {
    @Container static JeeContainer CONTAINER = new WildflyContainer("20.0.1.Final")
            .withDeployment("target/app-backend.war");

    @Override WebTarget target() {
        return CONTAINER.target();
    }

    // TODO expose management port in JeeTestContainers
    @Override WebTarget management() {
        assumeTrue(CONTAINER == null, "management port not yet exposed by wildfly");
        return null;
    }
}
