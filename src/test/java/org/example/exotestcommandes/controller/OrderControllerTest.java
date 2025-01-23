package org.example.exotestcommandes.controller;

import netscape.javascript.JSObject;
import org.example.exotestcommandes.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.List;

@WebFluxTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    private WebTestClient webClient;

    @Test
    void testGetOrders(){
        List<Order> expectedOrders = Arrays.asList(
                new Order(1,"Laptop"),
                new Order(2,"Phone")
        );

        webClient.get().uri("/api/orders")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Order.class)
                .isEqualTo(expectedOrders);
    }

    @Test
    void testPostOrder(){
        Order order = new Order("Tablet");
        Order createdOrder = new Order(3,"Tablet");

        webClient.post().uri("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(order)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Order.class).isEqualTo(createdOrder);

        webClient.get().uri("/api/orders")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Order.class)
                .contains(createdOrder);
    }
}
