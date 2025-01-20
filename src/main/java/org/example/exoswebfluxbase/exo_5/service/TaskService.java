package org.example.exoswebfluxbase.exo_5.service;

import org.example.exoswebfluxbase.exo_5.entity.Task;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class TaskService {
    private final Map<Integer,Task> tasks;
    private int count;

    public TaskService() {
        tasks = new HashMap<>();
        count = 0;
    }

    public Flux<Task> getTasks() {
        return Flux.fromIterable(tasks.values());
    }

    public Mono<Task> getTaskById(int id){
        return Mono.justOrEmpty(tasks.get(id));
    }

    public Mono<Task> createTask(Task task){
        task.setId(++count);
        tasks.put(count, task);
        return Mono.just(task);
    }

    public Mono<Task> updateTask(int id, Task task){
        if (tasks.containsKey(id)) {
            tasks.put(id, task);
            return Mono.just(task);
        }
        return Mono.empty();
    }

    public Mono<Boolean> deleteTask(int id){
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            return Mono.just(true);
        }
        return Mono.just(false);
    }
}
