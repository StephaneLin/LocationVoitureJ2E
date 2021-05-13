package BLV.servlet;

import BLV.entity.Car;
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


@WebServlet("/available_cars")
public class AvailableCarsServlet extends GenericServlet {

    private final ParkingService parkingService = ParkingService.getInstance();
    private final UserService userService = UserService.getInstance();
    private String begginingDate;
    private String endingDate;
    private int parkingId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String identifierConnectedUser = (String) req.getSession().getAttribute("email");

        boolean isUserConnected = identifierConnectedUser != null;


        if (isUserConnected) {
            int userRole = userService.checkRole(identifierConnectedUser);
            context.setVariable("userRole", userRole);
        }

        begginingDate = (String) req.getAttribute("begDate");
        endingDate = (String) req.getAttribute("endDate");
        parkingId = (int) req.getAttribute("parkingId");

        List<Car> carList = parkingService.listCarsFromParking(begginingDate, endingDate, parkingId);

        context.setVariable("CarList", carList);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("available_cars", context, resp.getWriter());
        parkingId = 0;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if (parkingId != 0) {
            int carId = Integer.parseInt(req.getParameter("carId"));
            req.setAttribute("carId", carId);

            if (req.getAttribute("action").equals("rent")) {
                req.setAttribute("begDate", begginingDate);
                req.setAttribute("endDate", endingDate);
                req.setAttribute("parkingId", parkingId);

                RequestDispatcher myDispatch = req.getRequestDispatcher("connected/user/booking");
                myDispatch.forward(req, resp);
            } else if (req.getAttribute("action").equals("modify") && (int) context.getVariable("userRole") > 1) {
                RequestDispatcher myDispatch = req.getRequestDispatcher("connected/staff/car_edition");
                myDispatch.forward(req, resp);
            }
        } else {
            this.doGet(req, resp);
        }
    }
}
