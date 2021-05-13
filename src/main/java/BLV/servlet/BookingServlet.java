package BLV.servlet;

import BLV.entity.Meeting;
import BLV.entity.User;
import BLV.service.BookingService;
import BLV.service.MeetingService;
import BLV.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalTime;


@WebServlet("/user/booking")
public class BookingServlet extends GenericServlet {

    private final UserService userService = UserService.getInstance();
    private final BookingService bookingService = BookingService.getInstance();
    private final MeetingService meetingService = MeetingService.getInstance();
    private String begginingDate;
    private String endingDate;
    private int parkingId;
    private int carId;
    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String identifierConnectedUser = (String) req.getSession().getAttribute("email");

        boolean isUserConnected = identifierConnectedUser != null;

        user = UserService.getInstance().getUserByEmail(identifierConnectedUser);
        context.setVariable("user", user);

        if (isUserConnected) {
            int userRole = userService.checkRole(identifierConnectedUser);
            context.setVariable("userRole", userRole);
        }

        begginingDate = (String) req.getAttribute("begDate");
        endingDate = (String) req.getAttribute("endDate");
        parkingId = (int) req.getAttribute("parkingId");
        carId = (int) req.getAttribute("carId");

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("connected/user/booking", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        Meeting meeting = meetingService.addMeeting(Date.valueOf(begginingDate));

        bookingService.addBooking(Date.valueOf(begginingDate), Date.valueOf(endingDate), 0, Date.valueOf(LocalTime.now().toString()), carId, meeting.getMeetingId(), user.getUserId());

        resp.sendRedirect("connected/user/booked");
    }
}
