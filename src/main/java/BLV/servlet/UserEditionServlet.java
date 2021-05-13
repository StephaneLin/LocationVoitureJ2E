package BLV.servlet;

import BLV.entity.User;
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

@WebServlet("/connected/staff/manager/user_edition")
public class UserEditionServlet extends GenericServlet {

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

        int userId = (int) req.getAttribute("userId");
        context.setVariable("user", userService.getUserById(userId));

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("connected/staff/manager/user_edition", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        if (req.getParameter("userLastName") != null) {
            String nameInput = req.getParameter("userLastName");
            String firstNameInput = req.getParameter("userFirstName");
            int phone = Integer.parseInt(req.getParameter("userPhone"));
            String emailInput = req.getParameter("email");
            String passwordInput = req.getParameter("password");
            Part filePart = req.getPart("license");
            int accessRights = Integer.parseInt(req.getParameter("accessRightsFK"));

            InputStream input = filePart.getInputStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] license = new byte[10240];
            for (int length = 0; (length = input.read(license)) > 0; ) output.write(license, 0, length);

            User user = new User(nameInput, firstNameInput, emailInput, passwordInput, license, phone, accessRights);
            userService.update(user);
            resp.sendRedirect("users_info");
        } else {
            this.doGet(req, resp);
        }
    }
}
