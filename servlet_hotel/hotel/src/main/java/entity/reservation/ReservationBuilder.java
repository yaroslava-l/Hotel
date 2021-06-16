package entity.reservation;

import entity.room.Room;
import entity.user.User;

import java.time.LocalDate;

public class ReservationBuilder {
    private Long id;

    private Integer places;

    private String type;

    private User userId;

    private String status;

    private Room roomId;

    private LocalDate checkIn;

    private LocalDate checkOut;

    public ReservationBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ReservationBuilder setPlaces(Integer places) {
        this.places = places;
        return this;
    }

    public ReservationBuilder setType(String type) {
        this.type = type;
        return this;

    }

    public ReservationBuilder setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        return this;

    }

    public ReservationBuilder setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
        return this;

    }

    public ReservationBuilder setUserId(User userId) {
        this.userId = userId;
        return this;

    }

    public ReservationBuilder setStatus(String status) {
        this.status = status;
        return this;

    }

    public ReservationBuilder setRoomId(Room roomId) {
        this.roomId = roomId;
        return this;

    }

    public Reservation build() {
        return new Reservation(id, places, type, userId, roomId, status, checkIn, checkOut);
    }
}
