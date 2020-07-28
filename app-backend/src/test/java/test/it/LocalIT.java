package test.it;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

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