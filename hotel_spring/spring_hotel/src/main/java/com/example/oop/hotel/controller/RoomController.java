package com.example.oop.hotel.controller;

import com.example.oop.hotel.dto.RoomsDto;
import com.example.oop.hotel.services.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rooms")
@CrossOrigin( "http://localhost:4200")
@Slf4j
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping()
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<RoomsDto> findAllRooms() {
        log.info("find All Rooms");
        return ResponseEntity.ok(roomService.getAllRooms());
    }


}
