package com.example.orderdomain.repository;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.orderdomain.domain.Order;

class DbOrderRepositoryBehavior {
    private static final EntityManager ENTITY_MANAGER = Persistence
            .createEntityManagerFactory("orders-test")
            .createEntityManager();

    private final DbOrderRepository repository = new DbOrderRepository();

    @BeforeEach
    void setUp() {
        repository.entityManager = ENTITY_MANAGER;
    }

    @Test
    void shouldFailToGetOrderByUnknownId() {
        Throwable throwable = catchThrowable(() -> repository.getOrderById(-1));

        then(throwable).isInstanceOf(NoResultException.class);
    }

    @Test
    void shouldGetOrderById() {
        Order existing = given(Order.builder().build());

        Order found = repository.getOrderById(existing.getId());

        then(found).usingRecursiveComparison().isEqualTo(existing);
    }

    private Order given(Order order) {
        EntityTransaction transaction = ENTITY_MANAGER.getTransaction();
        transaction.begin();
        try {
            return ENTITY_MANAGER.merge(order);
        } finally {
            transaction.commit();
        }
    }
}
