package com.example.oop.hotel.controller;

import com.example.oop.hotel.dto.ReservationDTO;
import com.example.oop.hotel.entities.Reservation;
import com.example.oop.hotel.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")

public class ReservController {

    private final OrderService reservService;

    public ReservController(OrderService reservService) {

        this.reservService = reservService;
    }

    @GetMapping("/order")
    public ResponseEntity<List<Reservation>> getAllReservs() {

        return ResponseEntity.ok(reservService.getAllOrders());
    }

    @PostMapping("/order")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Reservation> createNewReserv(@RequestBody ReservationDTO reservation) {
        try {
            return ResponseEntity.ok(reservService.createOrder(reservation));
        } catch (Exception e) {
            log.error("order not be create");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
