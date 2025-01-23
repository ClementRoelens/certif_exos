package org.example.exometricsreactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @GetMapping("/stream")
    public Flux<String> stream() {
        AtomicInteger i = new AtomicInteger(0);

        return Flux.interval(Duration.ofSeconds(500))
                .map(interval -> "Task " + i.incrementAndGet());

    }
}
