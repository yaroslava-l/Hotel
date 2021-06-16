package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.reservation.Reservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ReservationService;
import util.JsonConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/reservation")
public class ReservationServlet extends HttpServlet {
    static final Logger logger = LogManager.getLogger(LoginServlet.class);
    private final ReservationService service = new ReservationService();

    public ReservationServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Executing reservation get controller");
        try {
            List<Reservation> list = service.findAllReservations();
            JsonConverter.makeJsonAnswer(list, resp);
        } catch (Exception e) {
            logger.error("Can`t receive reservations: {}", e.getMessage());
            resp.sendError(400);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Executing reservation post controller");
        try {
            String s = JsonConverter.jsonBodyFromRequest(req, resp).get("reservation").toString();
            Reservation reservation = service.createNewReserve(new ObjectMapper()
                    .readValue(s, Reservation.class));
            JsonConverter.makeJsonAnswer(reservation, resp);
        } catch (Exception e) {
            logger.error("Can`t create reservation: {}", e.getMessage());
            resp.sendError(400, "Something went wrong on server");
        }
    }
}
