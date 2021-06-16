package com.example.oop.hotel.dto;

import com.example.oop.hotel.entities.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Data transfer object for creating reservation
 */

@Data
@NoArgsConstructor
public class ReservationDTO {
    @JsonProperty()
    private Integer places;
    @JsonProperty()
    private String room_type;
    @JsonProperty()
    private LocalDate checkIn;
    @JsonProperty()
    private LocalDate checkOut;
    @JsonProperty
    private Status status;
    @JsonProperty
    private Long userId;
}
