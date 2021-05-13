package BLV.servlet;

import BLV.entity.Car;
import BLV.service.CarService;
import BLV.service.MaintenanceService;
import BLV.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/connected/staff/car_review")
public class CarReviewServlet extends GenericServlet {

    private final CarService carService = CarService.getInstance();
    private final UserService userService = UserService.getInstance();
    private final MaintenanceService maintenanceService = MaintenanceService.getInstance();

    private int carId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String identifierConnectedUser = (String) req.getSession().getAttribute("email");

        boolean isUserConnected = identifierConnectedUser != null;

        if (isUserConnected) {
            int userRole = userService.checkRole(identifierConnectedUser);
            context.setVariable("userRole", userRole);
        }

        Car car = carService.findCarById(carId);

        context.setVariable("car", car);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("connected/staff/car_review", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        if (req.getParameter("notConform") != null) {
            String tireString = req.getParameter("tire");
            String bodyString = req.getParameter("bodywork");
            String electronicString = req.getParameter("electronics");
            String motorString = req.getParameter("motor");

            boolean tire = false;
            boolean body = false;
            boolean electronic = false;
            boolean motor = false;

            if (tireString != null) {
                tire = true;
            }
            if (bodyString != null) {
                body = true;
            }
            if (electronicString != null) {
                electronic = true;
            }
            if (motorString != null) {
                motor = true;
            }

            maintenanceService.addMaintenance(tire, body, electronic, motor, false, carId);

            resp.sendRedirect("cars_info");
        } else if (req.getParameter("conform") != null) {
            maintenanceService.addMaintenance(true, true, true, true, true, carId);
        } else {
            carId = (int) req.getAttribute("carId");
            resp.sendRedirect(req.getContextPath() + "/connected/staff/car_review");
        }
    }
}
