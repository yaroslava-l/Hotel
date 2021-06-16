package service;

import dao.RoomDao;
import dataConnection.ConnectionPool;
import entity.room.Room;

import java.sql.SQLException;
import java.util.List;

public class RoomService {

    private final ConnectionPool connectionPool = new ConnectionPool();

    public RoomService(){
    }

    public List<Room> findAllRooms() throws Exception {
        try (RoomDao roomDao = new RoomDao(connectionPool.getConnection())) {
            return roomDao.findAll();
        }
    }

}
