package test;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;

import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.example.frontend.domain.Order;

public class LoadGenerator implements Runnable {
    public static void main(String... args) {
        new LoadGenerator().run();
    }

    @Override public void run() {
        String json = BASE_TARGET.path("/orders/1").request(APPLICATION_JSON_TYPE).get(String.class);

        Order order = JsonbBuilder.create().fromJson(json, Order.class);

        System.out.println(order);
    }

    private static final WebTarget BASE_TARGET = ClientBuilder.newClient().target("http://localhost:8080/frontend");
}
