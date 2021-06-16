package com.example.oop.hotel.repositories;

import com.example.oop.hotel.entities.Reservation;
import com.example.oop.hotel.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> getAllByUserId(User userId);

    List<Reservation> findAllByOrderByStatusDesc();

}
