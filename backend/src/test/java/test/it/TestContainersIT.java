package test.it;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import javax.ws.rs.client.WebTarget;

import org.junit.jupiter.api.Disabled;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.github.t1.testcontainers.jee.JeeContainer;
import com.github.t1.testcontainers.jee.WildflyContainer;

// TODO init the database and configure the datasource
@Disabled
@Testcontainers
public class TestContainersIT extends AbstractIT {
    @Container static PostgreSQLContainer<?> DB = new PostgreSQLContainer<>(PostgreSQLContainer.IMAGE);
    @Container static JeeContainer CONTAINER = new WildflyContainer("20.0.1.Final")
            .dependsOn(DB)
            .withDeployment("target/backend.war");

    @Override WebTarget target() {
        return CONTAINER.target();
    }

    // TODO expose management port in JeeTestContainers
    @Override WebTarget management() {
        assumeTrue(CONTAINER == null, "management port not yet exposed by wildfly");
        return null;
    }
}
