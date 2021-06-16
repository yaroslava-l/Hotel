import dao.RoomDao;
import org.junit.Before;
import org.junit.Test;
import service.RoomService;

import java.sql.SQLException;
import java.util.LinkedList;

import static org.mockito.Mockito.*;

public class RoomServiceTest {
    private RoomService roomService;
    private final RoomDao roomDao = mock(RoomDao.class);

    @Before
    public void init() throws SQLException {
        roomService = new RoomService();
    }

    @Test
    public void shouldGetAllRooms() throws Exception {
        when(roomDao.findAll()).thenReturn(new LinkedList<>());
        roomService.findAllRooms();
        verify(roomDao, times(1)).findAll();
    }
}
