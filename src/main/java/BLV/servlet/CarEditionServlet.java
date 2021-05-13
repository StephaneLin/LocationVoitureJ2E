package BLV.servlet;

import BLV.entity.Car;
import BLV.service.CarService;
import BLV.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


@WebServlet("/connected/staff/manager/car_edition")
public class CarEditionServlet extends GenericServlet {

    private final CarService carService = CarService.getInstance();
    private final UserService userService = UserService.getInstance();

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
        templateEngine.process("connected/staff/manager/car_edition", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        if (req.getParameter("brand") != null) {
            String brand = req.getParameter("brand");
            String model = req.getParameter("model");
            int year = Integer.parseInt(req.getParameter("year"));
            int mileage = Integer.parseInt(req.getParameter("mileage"));
            int price = Integer.parseInt(req.getParameter("price"));
            int parking = Integer.parseInt(req.getParameter("parking"));
            Part filePart = req.getPart("carPicture");

            if (filePart == null) {
                carService.editCar(brand, model, year, mileage, price, null, parking, carId);
            } else {
                InputStream input = filePart.getInputStream();
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] picture = new byte[10240];
                for (int length = 0; (length = input.read(picture)) > 0; ) output.write(picture, 0, length);

                carService.editCar(brand, model, year, mileage, price, picture, parking, carId);
            }
            resp.sendRedirect("../cars_info");
        } else {
            carId = (int) req.getAttribute("carId");
            resp.sendRedirect(req.getContextPath() + "/connected/staff/manager/car_edition");
        }
    }
}
