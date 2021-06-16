package com.example.oop.hotel.services;

import com.example.oop.hotel.dto.ReservationDTO;
import com.example.oop.hotel.entities.Reservation;
import com.example.oop.hotel.entities.Room;
import com.example.oop.hotel.entities.Status;
import com.example.oop.hotel.entities.User;
import com.example.oop.hotel.repositories.OrderRepository;
import com.example.oop.hotel.repositories.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Reservation service class
 */
@Service
@Slf4j
public class OrderService {
    private final RoomRepository roomRepository;
    private final OrderRepository orderRepository;
    private final UserService userService;

    public List<Reservation> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Reservation> getAllOrdersByUser(User user) {
        return orderRepository.getAllByUserId(user);
    }

    public OrderService(OrderRepository orderRepository, UserService userService, RoomRepository roomRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.roomRepository = roomRepository;
    }

    public Reservation createOrder(ReservationDTO reservationDTO)
            throws Exception {
        try {
            Reservation reservation = Reservation.builder()
                    .room_type(reservationDTO.getRoom_type())
                    .places(reservationDTO.getPlaces())
                    .checkIn(reservationDTO.getCheckIn())
                    .checkOut(reservationDTO.getCheckOut())
                    .status(reservationDTO.getStatus())
                    .userId(new User().builder().id(reservationDTO.getUserId()).build())
                    .build();;
            return orderRepository.save(reservation);

        } catch (Exception e) {
            log.error("User cannot create order");
            throw new Exception("User cannot create order");
        }
    }

    public void deleteById(Long id) throws Exception {
        try {
            orderRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Order id must not be null, {}", e.getMessage());
            throw new Exception("Order id must not be null");
        }
    }

    @Transactional
    public Reservation updateOrder(Long reservationId, Long roomdId) throws Exception {
        try {
            Reservation reservation = orderRepository.findById(reservationId)
                    .orElseThrow(() -> new Exception("Can`t find user"));
            Room room = roomRepository.findById(roomdId)
                    .orElseThrow(() -> new Exception("Can`t find room"));
            return orderRepository.save(reservation.addStatusAndRoom(Status.CONFIRMED, room));
        } catch (Exception e) {
            log.info("Can`t update user: {}", e.getMessage());
            throw new Exception("Can`t update order");
        }
    }

}
