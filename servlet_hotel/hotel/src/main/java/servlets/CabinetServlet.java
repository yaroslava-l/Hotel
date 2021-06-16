package servlets;

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

@WebServlet(urlPatterns = "/cabinet")
public class CabinetServlet extends HttpServlet {
    static final Logger logger = LogManager.getLogger(CabinetServlet.class);
    private final ReservationService service = new ReservationService();

    public CabinetServlet() {
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Executing cabinet get controller");
        try {
            List<Reservation> Reservations = service.findAllReservationByUser(
                    Long.parseLong(req.getParameter("user")));
            JsonConverter.makeJsonAnswer(Reservations, resp);

        } catch (Exception ex) {

            logger.info("Reservation can`t be founded: {}", ex.getMessage());
            resp.sendError(400);
        }
    }
}
