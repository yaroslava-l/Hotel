package com.example.oop.hotel.controller;

import com.example.oop.hotel.entities.Reservation;
import com.example.oop.hotel.entities.User;
import com.example.oop.hotel.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Controller
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")

public class CabinetController {

    private final OrderService reservService;

    public CabinetController(OrderService orderService) {
        this.reservService = orderService;
    }

    @PostMapping("/cabinet")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity getAllReservsByUser(@RequestBody User user) {
        List<Reservation> orders = reservService.getAllOrdersByUser(user);
        return ResponseEntity.ok(orders);
    }
}
