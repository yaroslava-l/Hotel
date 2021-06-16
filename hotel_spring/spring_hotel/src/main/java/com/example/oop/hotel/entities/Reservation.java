package com.example.oop.hotel.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "places")
    private Integer places;

    @Basic
    @Column(name = "room_type")
    private String room_type;

    @Basic
    @Column(name = "check_in")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate checkIn;

    @Basic
    @Column(name = "check_out")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate checkOut;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User userId;

    @Basic
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room roomId;


    public Reservation addStatusAndRoom(Status status, Room room) {
        this.setStatus(status);
        this.setRoomId(room);
        return this;
    }


}
