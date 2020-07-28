package test.it;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.assertj.core.api.BDDAssertions.then;

import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

class LocalIT extends AbstractIT {
    private static final Client CLIENT = ClientBuilder.newClient();

    @Override WebTarget target() {
        return CLIENT.target("http://localhost:8080/app-backend");
    }

    @Override WebTarget management() {
        return CLIENT.target("http://localhost:9990");
    }

    @Data @Builder @NoArgsConstructor @AllArgsConstructor
    public static class Health {
        String status;
    }
}
