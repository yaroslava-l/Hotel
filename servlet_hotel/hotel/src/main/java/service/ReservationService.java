package service;

import dao.ReservationDao;
import dataConnection.ConnectionPool;
import entity.reservation.Reservation;

import java.sql.SQLException;
import java.util.List;

public class ReservationService {
    private final ConnectionPool connectionPool = new ConnectionPool();

    public ReservationService(){
    }

    public Reservation createNewReserve(Reservation reservation) throws Exception {
        try (ReservationDao reservationDao = new ReservationDao(connectionPool.getConnection())) {
            return reservationDao.create(reservation)
                    .orElseThrow(() -> new Exception("reservation cannot be created"));
        }
    }
    public List<Reservation> findAllReservationByUser(Long id) throws Exception {
        try (ReservationDao reservationDao = new ReservationDao(connectionPool.getConnection())) {
            return reservationDao.findReservationsByUser(id);
        }
    }

    public List<Reservation> findAllReservations() throws Exception {
        try (ReservationDao reservationDao = new ReservationDao(connectionPool.getConnection())) {
            return reservationDao.findAll();
        }
    }

    public Reservation updateReservation(Reservation reservation) throws Exception {
        try (ReservationDao reservationDao = new ReservationDao(connectionPool.getConnection())) {
            return reservationDao.update(reservation)
                    .orElseThrow(() -> new Exception("Reservation can`t be updated"));
        }
    }

}
