package org.example.exoswebfluxbase.exo_4;

import org.example.exoswebfluxbase.exo_4.entity.Article;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/articles")
public class ArticleController {
    @GetMapping
    public Flux<Article> getArticles() {
        return Flux.just(
                new Article("Introduction to Spring WebFlux"),
                new Article("Reactive Programming with Project Reactor"),
                new Article("Building APIs with Spring Boot")
        );
    }
}
