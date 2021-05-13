package BLV.servlet;

import BLV.entity.User;
import BLV.exception.UserIsNotSignedUpException;
import BLV.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/connexion")
public class ConnexionServlet extends GenericServlet {

    static final Logger LOGGER = LogManager.getLogger();
    public static boolean isUserLogged;
    public static int userRoleLevel;
    private final UserService userService = UserService.getInstance();
    private boolean isError;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        if (isUserLogged) {
            response.sendRedirect("index");
        } else {
            WebContext context = new WebContext(request, response, request.getServletContext());
            context.setVariable("error", isError);
            TemplateEngine templateEngine = createTemplateEngine(request.getServletContext());
            templateEngine.process("connexion", context, response.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebContext context = new WebContext(request, response, request.getServletContext());
        List<User> listOfUsers = userService.listUsers();
        context.setVariable("UserList", listOfUsers);

        String formEmail = request.getParameter("email");
        String formPassword = request.getParameter("password");
        try {
            User user = userService.checkPasswordAndReturnUser(formEmail, formPassword);
            if (user != null) {
                request.getSession().setAttribute("email", user.getEmail());
                userRoleLevel = user.getAccessRightsFK();
                isUserLogged = true;
                isError = false;
            } else {
                isError = true;
            }
        } catch (UserIsNotSignedUpException e) {
            isError = true;
            LOGGER.error("User is not connected", e);
        }
        this.doGet(request, response);
    }
}
