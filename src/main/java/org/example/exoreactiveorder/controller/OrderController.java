package org.example.exoreactiveorder.controller;

import org.example.exoreactiveorder.entity.Order;
import org.example.exoreactiveorder.enums.OrderStatus;
import org.example.exoreactiveorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @GetMapping
    public Flux<Order> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Mono<Order> getOrder(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/search")
    public Flux<Order> searchOrder(@RequestParam String status) {
        return orderService.searchByStatus(OrderStatus.valueOf(status));
    }

    @GetMapping("/paged")
    public Flux<Order> pagedOrder(@RequestParam int page, @RequestParam int size) {
        return orderService.getPagedOrders(page, size);
    }

    @PostMapping
    public Mono<Order> createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/{id}")
    public Mono<Order> updateOrderStatus(@PathVariable int id, @RequestBody Order order) {
        return orderService.updateOrder(id, order.getStatus());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        return Mono.empty();
    }


}
