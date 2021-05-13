package BLV.servlet;

import BLV.entity.Booking;
import BLV.entity.User;
import BLV.service.BookingService;
import BLV.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/connected/user/booked")
public class BookedServlet extends GenericServlet {

    private final UserService userService = UserService.getInstance();
    private final BookingService bookingService = BookingService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String identifierConnectedUser = (String) req.getSession().getAttribute("email");

        boolean isUserConnected = identifierConnectedUser != null;

        if (isUserConnected) {
            int userRole = userService.checkRole(identifierConnectedUser);
            context.setVariable("userRole", userRole);
        }

        User user = userService.getUserByEmail(identifierConnectedUser);
        List<Booking> bookingList = bookingService.listBookingsFromUserId(user);

        context.setVariable("BookingList", bookingList);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("connected/user/booked", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        if (req.getParameter("bookIdMod") != null) {
            int bookingId = Integer.parseInt(req.getParameter("bookIdSupp"));
            Booking booking = bookingService.findById(bookingId);

            bookingService.delete(booking);
            this.doGet(req, resp);
        }
    }
}
