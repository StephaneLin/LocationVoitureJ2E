package BLV.servlet;

import BLV.entity.User;
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


@WebServlet("/connected/staff/manager/users_info")
public class UsersInfoServlet extends GenericServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String identifierConnectedUser = (String) req.getSession().getAttribute("email");

        boolean isUserConnected = identifierConnectedUser != null;
        context.setVariable("isUserConnected", isUserConnected);

        if (isUserConnected) {
            int userRole = userService.checkRole(identifierConnectedUser);
            context.setVariable("userRole", userRole);
        }

        List<User> userList = null;
        try {
            userList = userService.findUsersForManagement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        context.setVariable("UsersList", userList);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("connected/staff/manager/users_info", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        if (req.getParameter("userIdMod") != null && req.getParameter("userTypeMod").equals("0")) {
            int userId = Integer.parseInt(req.getParameter("userIdMod"));
            req.setAttribute("userId", userId);

            RequestDispatcher myDispatch = req.getRequestDispatcher("user_edition");
            myDispatch.forward(req, resp);
        } else if (req.getParameter("userIdMod") != null && req.getParameter("userTypeMod").equals("1")) {
            int userId = Integer.parseInt(req.getParameter("userIdMod"));
            req.setAttribute("userId", userId);

            RequestDispatcher myDispatch = req.getRequestDispatcher("company_edition");
            myDispatch.forward(req, resp);
        } else if (req.getParameter("userIdSupp") != null) {
            int userId = Integer.parseInt(req.getParameter("userIdSupp"));
            userService.delete(userService.findById(userId));

            this.doGet(req, resp);
        }
    }
}
