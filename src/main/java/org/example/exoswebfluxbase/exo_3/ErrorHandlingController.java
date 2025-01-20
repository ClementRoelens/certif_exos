package org.example.exoswebfluxbase.exo_3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/")
public class ErrorHandlingController {
    @GetMapping("error-resume")
    public Flux<String> errorResume(){
        return Flux.just("A","B","C")
                .map(value -> {
                    if (value.equals("C")){
                        throw new RuntimeException("Erreur lors du traitement de " + value);
                    }
                    return value;
                })
                .onErrorResume(e -> Flux.just("Default 1", "Default 2"));
    }

    @GetMapping("error-continue")
    public Flux<Integer> errorContinue(){
        return Flux.range(1,5)
                .map(value -> {
                    if (value == 2){
                        throw new RuntimeException(value + " n'a pas pu être traité");
                    }
                    return value;
                })
                .onErrorContinue((e, value) -> System.out.println("Erreur avec " + value + " : " + e.getMessage()));
    }
}
