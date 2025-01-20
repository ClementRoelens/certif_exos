package org.example.exoswebfluxbase.exo_2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/processed-numbers")
public class OperationController {
    @GetMapping
    public Flux<String> getProcessedNumbers() {
        return Flux.range(1,10)
                .filter(n -> n % 2 == 0)
                .map(n -> {
                    n *= 10;
                    return "Processed : " + n;
                });
    }
}
