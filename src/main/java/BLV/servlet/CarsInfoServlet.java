package BLV.servlet;

import BLV.entity.Car;
import BLV.service.CarService;
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

@WebServlet("/connected/staff/cars_info")
public class CarsInfoServlet extends GenericServlet {

    private final CarService carService = CarService.getInstance();
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

        List<Car> carList = carService.listCars();

        context.setVariable("CarsList", carList);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("connected/staff/cars_info", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if (req.getParameter("carIdMod") != null) {
            int carId = Integer.parseInt(req.getParameter("carIdMod"));
            req.setAttribute("carId", carId);

            RequestDispatcher myDispatch = req.getRequestDispatcher("manager/car_edition");
            myDispatch.forward(req, resp);
        } else if (req.getParameter("carIdSupp") != null) {
            int carId = Integer.parseInt(req.getParameter("carIdSupp"));

            carService.delete(carService.findCarById(carId));

            this.doGet(req, resp);
        } else if (req.getParameter("carIdRev") != null) {
            int carId = Integer.parseInt(req.getParameter("carIdRev"));
            req.setAttribute("carId", carId);

            RequestDispatcher myDispatch = req.getRequestDispatcher("car_review");
            myDispatch.forward(req, resp);
        }
    }
}
