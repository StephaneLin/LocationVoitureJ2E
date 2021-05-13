package BLV.servlet;

import BLV.entity.Parking;
import BLV.service.ParkingService;
import BLV.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/booking_base")
public class BookingBaseServlet extends GenericServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String identifierConnectedUser = (String) req.getSession().getAttribute("email");

        boolean isUserConnected = identifierConnectedUser != null;

        if (isUserConnected) {
            int userRole = userService.checkRole(identifierConnectedUser);
            context.setVariable("userRole", userRole);
        }

        List<Parking> parkingList = ParkingService.getInstance().listParkings();
        context.setVariable("ParkingList", parkingList);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("booking_base", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String begginingDate = req.getParameter("reservationBeg");
        String endingDate = req.getParameter("reservationEnd");
        int parkingId = Integer.parseInt(req.getParameter("parkingSelect"));

        req.setAttribute("begDate", begginingDate);
        req.setAttribute("endDate", endingDate);
        req.setAttribute("parkingId", parkingId);

        RequestDispatcher myDispatch = req.getRequestDispatcher("available_cars");
        myDispatch.forward(req, resp);
    }
}
