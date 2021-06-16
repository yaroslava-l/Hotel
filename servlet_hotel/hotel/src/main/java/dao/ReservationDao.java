package dao;

import TransactionManger.TransactionManager;
import entity.reservation.Reservation;
import entity.reservation.ReservationBuilder;
import entity.reservation.Status;
import entity.room.Room;
import entity.room.RoomBuilder;
import entity.user.UserBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationDao implements AutoCloseable {

    private final Logger logger = LogManager.getLogger(ReservationDao.class);
    private final Connection connection;
    private final RoomDao roomDao;

    public ReservationDao(Connection connection) {
        this.connection = connection;
        roomDao = new RoomDao(connection);
    }

    public Optional<Reservation> create(Reservation entity){
        ResultSet set = null;
        try {
            PreparedStatement sql= connection.prepareStatement("INSERT INTO reservation (places, room_type, check_in , check_out , user_id, status) VALUES (?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            sql.setInt(1, entity.getPlaces());
            sql.setString(2, entity.getType());
            sql.setDate(3, Date.valueOf(entity.getCheckIn()));
            sql.setDate(4, Date.valueOf(entity.getCheckOut()));
            sql.setLong(5, entity.getUserByUserId().getId());
            sql.setString(6, entity.getStatus());
            sql.executeUpdate();
            set = sql.getGeneratedKeys();
            if (set.next()) {
                entity.setId(set.getLong("id"));
            }
            return Optional.of(entity);

        } catch (SQLException ex) {
            logger.warn("Reservation can`t be created: {}", ex.getMessage());
        }
        return Optional.empty();
    }

    public Reservation findById(long id) {
        return null;
    }

    public List<Reservation> findAll() throws SQLException {
        List<Reservation> reservationList = new ArrayList<>();
        ResultSet set;
        try {
            PreparedStatement sql= connection.prepareStatement("SELECT c.*, p.email, a.name  from public.reservation as c inner join public.user as p on c.user_id=p.id left join public.room as a on c.room_id=a.id");
            set = sql.executeQuery();
            while (set.next()) {
                Reservation reservation = new ReservationBuilder()
                        .setId(set.getLong("id"))
                        .setPlaces(set.getInt("places"))
                        .setStatus(set.getString("status"))
                        .setType(set.getString("room_type"))
                        .setCheckIn(set.getDate("check_in").toLocalDate())
                        .setCheckOut(set.getDate("check_out").toLocalDate())
                        .build();
                reservation.setUserByUserId(new UserBuilder()
                        .setId(set.getLong("user_id"))
                        .setEmail(set.getString("email"))
                        .build());
                reservation.setRoomId(new RoomBuilder()
                        .setId(set.getLong("room_id"))
                        .setName(set.getString("name"))
                        .build());
                reservationList.add(reservation);
            }
        } catch (SQLException ex) {
            logger.warn("Reservations can`t be find: {}", ex.getMessage());
        }
        return reservationList;
    }

    public List<Reservation> findReservationsByUser(Long id) throws SQLException {
        List<Reservation> reservationList = new ArrayList<>();
        ResultSet set;
        try {
            PreparedStatement sql= connection.prepareStatement("SELECT reservation.*, room.name  from reservation left join room on room.id=reservation.room_id  where user_id=?");
            sql.setLong(1, id);
            set = sql.executeQuery();
            while (set.next()) {
                Reservation reservation = new ReservationBuilder()
                        .setId(set.getLong("id"))
                        .setPlaces(set.getInt("places"))
                        .setStatus(set.getString("status"))
                        .setType(set.getString("room_type"))
                        .setCheckIn(set.getDate("check_in").toLocalDate())
                        .setCheckOut(set.getDate("check_out").toLocalDate())
                        .build();
                reservation.setRoomId(new RoomBuilder()
                        .setId(set.getLong("room_id"))
                        .setName(set.getString("name"))
                        .build());
                reservationList.add(reservation);
            }

        } catch (SQLException ex) {
            logger.warn("Reservations can`t be find by user: {}", ex.getMessage());
        }
        return reservationList;
    }


    public Optional<Reservation> update(Reservation entity) throws SQLException {
        try {
            connection.setAutoCommit(false);

            TransactionManager.beginTransaction(connection);

            Room room = roomDao.findById(entity.getRoomId().getId());

            updateReservationByRoomId(room.getId(), entity.getId());

            TransactionManager.commitTransaction(connection);

            return Optional.of(entity);

        } catch (SQLException ex) {
            TransactionManager.rollbackTransaction(connection);
            logger.warn("Reservation could not be created: {}", ex.getMessage());

            return Optional.empty();
        }
    }

    private void updateReservationByRoomId(long roomId, long reservationId) throws SQLException {
        try {
            PreparedStatement secondStatement = connection.prepareStatement("UPDATE reservation SET room_id= ?, status= ? WHERE id=?");
            secondStatement.setLong(1, roomId);
            secondStatement.setString(2, Status.CONFIRMED.getName());
            secondStatement.setLong(3, reservationId);
            secondStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Reservation could not be updated: {}", e.getMessage());
        }
    }

    public void close() throws SQLException {
        connection.close();

    }


}
