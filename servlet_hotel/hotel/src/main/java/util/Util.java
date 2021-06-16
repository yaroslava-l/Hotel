package util;

import entity.reservation.Reservation;
import entity.reservation.ReservationBuilder;
import entity.room.RoomBuilder;

import javax.servlet.http.HttpServletRequest;

public class Util {

    public static Reservation buildAdminReservation(HttpServletRequest request) {

        return new ReservationBuilder()
                .setRoomId(new RoomBuilder().setId(Long.parseLong(request.getParameter("room_id")) ).build())
                .setId(Long.parseLong(request.getParameter("id")))
                .build();
    }
}
