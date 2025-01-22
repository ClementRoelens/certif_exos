package org.example.exoreservationsalles.service;

import org.example.exoreservationsalles.entity.Room;
import org.example.exoreservationsalles.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;


    public Mono<Room> addRoom(Room room) {
        return roomRepository.save(room);
    }

    public Flux<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Mono<Void> deleteRoom(int id){
        return roomRepository.existsById(id)
                .flatMap(res -> {
                    if (res){
                        return roomRepository.deleteById(id);
                    }
                    return Mono.empty();
                });
    }
}
