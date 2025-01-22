package org.example.exoreactiveorder.repository;

import org.example.exoreactiveorder.entity.Order;
import org.example.exoreactiveorder.enums.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveCrudRepository<Order, Integer> {
    Flux<Order> findAllByStatus(OrderStatus status);
}
