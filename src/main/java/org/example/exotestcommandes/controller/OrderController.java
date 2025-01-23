package org.example.exotestcommandes.controller;

import org.example.exotestcommandes.entity.Order;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final List<Order> orders;

    public OrderController() {
        orders = new ArrayList<>();
        orders.add(new Order("Laptop"));
        orders.add( new Order("Phone"));
    }

    @GetMapping
    public Flux<Order> getOrders() {
        return Flux.fromIterable(orders);
    }

    @PostMapping
    public Mono<Order> createOrder(@RequestBody Order order) {
        orders.add(order);
        return Mono.just(order);
    }
}
