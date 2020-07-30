package test.it;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@EnabledIfSystemProperty(named = "test-stage", matches = "local")
class LocalIT extends AbstractIT {
    private static final Client CLIENT = ClientBuilder.newClient();

    @Override WebTarget target() {
        return CLIENT.target("http://localhost:8080/frontend");
    }

    @Override WebTarget management() {
        return CLIENT.target("http://localhost:9990");
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class Health {
        String status;
    }
}
