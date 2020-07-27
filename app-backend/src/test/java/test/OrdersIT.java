package test;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.assertj.core.api.BDDAssertions.then;

import javax.ws.rs.client.Invocation.Builder;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.github.t1.testcontainers.jee.JeeContainer;
import com.github.t1.testcontainers.jee.WildflyContainer;

@Testcontainers
public class OrdersIT {
    @Container static JeeContainer CONTAINER = new WildflyContainer("20.0.1.Final")
            .withDeployment("target/app-backend.war");

    @Test void shouldGetOrder() {
        Builder request = CONTAINER.target().path("/orders/1").request(APPLICATION_JSON_TYPE);

        String order = request.get(String.class);

        then(order).isEqualTo("{" +
                "\"customer\":{\"name\":\"Jane Doe\"}," +
                "\"id\":\"1\"," +
                "\"items\":[" +
                /**/"{\"count\":3,\"pieceCostInCent\":1234,\"product\":\"Lord Of The Rings\"}," +
                /**/"{\"count\":1,\"pieceCostInCent\":1150,\"product\":\"The Hobbit\"}" +
                "]}");
    }
}
