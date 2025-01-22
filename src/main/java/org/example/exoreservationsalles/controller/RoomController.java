package org.example.exoreservationsalles.controller;

import org.example.exoreservationsalles.entity.Room;
import org.example.exoreservationsalles.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping
    public Flux<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/test")
    public String test() {
        return "coucou";
    }

    @PostMapping
    public Mono<Room> createRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteRoom(@PathVariable int id) {
        return roomService.deleteRoom(id);
    }
}
