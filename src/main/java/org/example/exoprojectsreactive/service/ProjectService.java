package org.example.exoprojectsreactive.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectService {
   private final List<String> projects;

   public ProjectService() {
       projects = Arrays.asList("Projet A", "Projet B", "Projet C");
   }

    public Flux<String> getProjects() {
        return Flux.fromIterable(projects);
    }
}
