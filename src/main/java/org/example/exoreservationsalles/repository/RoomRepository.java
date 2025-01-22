package org.example.exoreservationsalles.repository;

import org.example.exoreservationsalles.entity.Room;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RoomRepository extends ReactiveCrudRepository<Room, Integer> {
}
