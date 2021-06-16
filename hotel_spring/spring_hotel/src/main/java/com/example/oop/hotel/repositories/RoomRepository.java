package com.example.oop.hotel.repositories;

import com.example.oop.hotel.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoomRepository extends JpaRepository<Room, Long> {
}
