package BLV.servlet;

import BLV.entity.Car;
import BLV.entity.Parking;
import BLV.entity.User;
import BLV.service.CarService;
import BLV.service.ParkingService;
import BLV.service.SearchService;
import BLV.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/connected/staff/manager/crud_tool")
public class CrudToolServlet extends GenericServlet {
    private final CarService carService = CarService.getInstance();
    private final UserService userService = UserService.getInstance();
    private final SearchService searchService = SearchService.getInstance();
    private final ParkingService parkingService = ParkingService.getInstance();

    private List<Car> searchResultsCar;
    private List<Parking> searchResultsParking;
    private List<User> searchResultsUser;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String identifierConnectedUser = (String) req.getSession().getAttribute("email");

        boolean isUserConnected = identifierConnectedUser != null;

        if (isUserConnected) {
            int userRole = userService.checkRole(identifierConnectedUser);
            context.setVariable("userRole", userRole);
        }

        String searchInput = (String) req.getAttribute("search_input");

        if (searchInput == null) {
            searchResultsCar = carService.listCars();
            searchResultsParking = parkingService.listParkings();
            searchResultsUser = null;
            try {
                searchResultsUser = userService.listUsersWithoutPassword();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            searchResultsParking = searchService.searchParking(searchInput);
            searchResultsUser = searchService.searchUser(searchInput);
            searchResultsCar = searchService.searchCar(searchInput);
        }

        context.setVariable("CarList", searchResultsCar);
        context.setVariable("UsersList", searchResultsUser);
        context.setVariable("ParkingsList", searchResultsParking);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("connected/staff/manager/crud_tool", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        if (req.getParameter("carIdMod") != null) {
            int carId = Integer.parseInt(req.getParameter("carIdMod"));
            req.setAttribute("carId", carId);

            RequestDispatcher myDispatch = req.getRequestDispatcher("car_edition");
            myDispatch.forward(req, resp);
        } else if (req.getParameter("carIdSupp") != null) {
            int carId = Integer.parseInt(req.getParameter("carIdSupp"));
            carService.delete(carService.findCarById(carId));

            this.doGet(req, resp);
        } else if (req.getParameter("userIdSupp") != null) {
            int userId = Integer.parseInt(req.getParameter("userIdSupp"));
            userService.delete(userService.findById(userId));

            this.doGet(req, resp);
        } else if (req.getParameter("search_input") != null) {
            String searchInput = req.getParameter("search_input");
            req.setAttribute("search_input", searchInput);

            this.doGet(req, resp);
        }
    }
}
