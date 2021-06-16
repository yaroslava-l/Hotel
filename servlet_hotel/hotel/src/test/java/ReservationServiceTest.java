import dao.ReservationDao;
import entity.reservation.Reservation;
import entity.reservation.ReservationBuilder;
import org.junit.Before;
import org.junit.Test;
import service.ReservationService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ReservationServiceTest {
    private ReservationService reservationService;
    private final ReservationDao reservationDao = mock(ReservationDao.class);

    Reservation reservation;

    @Before
    public void initReservation() throws SQLException {
        reservationService = new ReservationService();
       // reservationService.setFactoryDao(factoryDao);
        reservation = new ReservationBuilder()
                .setId(1L)
                .setType("lux")
                .setCheckIn(LocalDate.of(2020, Month.AUGUST, 23))
                .setCheckOut(LocalDate.of(2020, Month.AUGUST, 27))
                .setPlaces(3)
                .build();

    }

    @Test
    public void shouldCreateNewReservationTest() throws Exception {
        //when(factoryDao.createReservationDao()).thenReturn(reservationDao);
        when(reservationDao.create(reservation)).thenReturn(Optional.of(reservation));
        reservationService.createNewReserve(reservation);
        //verify(factoryDao, times(1)).createReservationDao();
        verify(reservationDao, times(1)).create(reservation);

    }

    @Test
    public void shouldFindAllReservationByUser() throws Exception {
      //  when(factoryDao.createReservationDao()).thenReturn(reservationDao);
        when(reservationDao.findReservationsByUser(1L)).thenReturn(new LinkedList<>());
        reservationService.findAllReservationByUser(1L);
       // verify(factoryDao, times(1)).createReservationDao();
        verify(reservationDao, times(1)).findReservationsByUser(1L);
    }

    @Test
    public void shouldFindAllReservations() throws Exception {
      //  when(factoryDao.createReservationDao()).thenReturn(reservationDao);
        when(reservationDao.findAll()).thenReturn(new LinkedList<>());
        reservationService.findAllReservations();
       // verify(factoryDao, times(1)).createReservationDao();
        verify(reservationDao, times(1)).findAll();
    }


}
