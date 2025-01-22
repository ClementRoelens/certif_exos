package org.example.exoreactiveorder.service;

import org.example.exoreactiveorder.entity.Order;
import org.example.exoreactiveorder.enums.OrderStatus;
import org.example.exoreactiveorder.repository.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class OrderService {
    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public Mono<Order> createOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
//        order.setStatus(OrderStatus.PENDING);
        return orderRepository.save(order);
    }

    public Flux<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Flux<Order> searchByStatus(OrderStatus status) {
        return orderRepository.findAllByStatus(status);
    }

    public Flux<Order> getPagedOrders(int page, int size) {
        return orderRepository.findAll()
                .skip(page * size)
                .take(size);
    }

    public Mono<Order> getOrderById(int id) {
        return orderRepository.findById(id);
    }

    public Mono<Order> updateOrder(int id, OrderStatus status) {
        return orderRepository.findById(id)
                .flatMap(order -> {
                    if (order != null) {
                        order.setStatus(status);
                        return orderRepository.save(order);
                    }
                    return Mono.empty();
                });
    }

    public Mono<Boolean> deleteOrder(int id) {
        return orderRepository.existsById(id)
                .flatMap(Mono::just);
    }
}
