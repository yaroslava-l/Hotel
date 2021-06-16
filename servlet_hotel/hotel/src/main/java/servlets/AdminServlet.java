package servlets;

import entity.reservation.Reservation;
import entity.reservation.ReservationBuilder;
import entity.room.RoomBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ReservationService;
import util.JsonConverter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    static final Logger logger = LogManager.getLogger(LoginServlet.class);
    private final ReservationService service = new ReservationService();

    public AdminServlet(){
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Executing reservation post controller");
        try {
            Reservation reservation = service.updateReservation(new ReservationBuilder()
                    .setRoomId(new RoomBuilder().setId(Long.parseLong(req.getParameter("room_id")) ).build())
                    .setId(Long.parseLong(req.getParameter("id")))
                    .build());
            JsonConverter.makeJsonAnswer(reservation, resp);
        } catch (Exception e) {
            logger.error("Can`t update reservation:{}", e.getMessage());
            resp.sendError(400);
        }
    }
}
