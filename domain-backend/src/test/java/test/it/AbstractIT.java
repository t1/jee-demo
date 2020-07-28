package test.it;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.assertj.core.api.BDDAssertions.then;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

import org.junit.jupiter.api.Test;

import com.example.orderdomain.domain.Order;
import com.example.orderdomain.domain.OrderItem;
import com.example.orderdomain.domain.Person;
import test.it.LocalIT.Health;

abstract class AbstractIT {
    @Test void shouldGetOrder() {
        Invocation.Builder request = target().path("/orders/1").request(APPLICATION_JSON_TYPE);

        Order order = GET(request, Order.class);

        then(order).usingRecursiveComparison().isEqualTo(Order.builder()
                .id("1")
                .customer(Person.builder()
                        .name("Jane Doe")
                        .build())
                .item(OrderItem.builder()
                        .count(3)
                        .pieceCostInCent(1234)
                        .product("Lord Of The Rings")
                        .build())
                .item(OrderItem.builder()
                        .count(1)
                        .pieceCostInCent(1150)
                        .product("The Hobbit")
                        .build())
                .build());
    }

    @Test void shouldGetHealthCheck() {
        Invocation.Builder request = management().path("/health").request(APPLICATION_JSON_TYPE);

        Health health = GET(request, Health.class);

        then(health).isEqualTo(Health.builder().status("UP").build());
    }

    abstract WebTarget target();

    abstract WebTarget management();

    <T> T GET(Invocation.Builder request, Class<T> type) {
        return JSONB.fromJson(request.get(String.class), type);
    }

    public static final Jsonb JSONB = JsonbBuilder.create();
}