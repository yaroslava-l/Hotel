package entity.reservation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import entity.room.Room;
import entity.user.User;
import util.LocalDateDeserializer;
import util.LocalDateSerializer;

import java.time.LocalDate;


public class Reservation {
    private Long id;

    private Integer places;

    private String type;

    private String status;

    private User userId;

    private Room roomId;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate checkIn;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate checkOut;

    public Reservation() {
    }

    public Reservation(Long id, Integer places, String type, User userId, Room roomId, String status, LocalDate checkIn, LocalDate checkOut) {
        this.id = id;
        this.places = places;
        this.type = type;
        this.status = status;
        this.userId = userId;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public User getUserByUserId() {
        return userId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userId = userByUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }
}
