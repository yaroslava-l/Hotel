
import dao.UserDao;
import entity.user.User;
import entity.user.UserBuilder;
import org.junit.Before;
import org.junit.Test;
import service.UserService;

import java.sql.SQLException;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService userService;
    private final UserDao userDao = mock(UserDao.class);
    private User user;

    @Before
    public void init() throws SQLException {
        userService = new UserService();

        user = new UserBuilder()
                .setId(1L)
                .setName("Alex")
                .setPassword("1")
                .setEmail("12@gmail.com")
                .build();
    }

    @Test
    public void shouldLoginUser() throws Exception {
        when(userDao.findByEmail(anyString())).thenReturn(Optional.of(user));
        userService.loginUser("name", "1");
        verify(userDao, times(1)).findByEmail(anyString());
    }
}



