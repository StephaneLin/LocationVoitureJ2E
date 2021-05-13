package BLV.servlet;

import BLV.entity.User;
import BLV.exception.EmailAlreadyTakenException;
import BLV.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/sign_up")
@MultipartConfig
public class SignUpServlet extends GenericServlet {
    static final Logger LOGGER = LogManager.getLogger();
    private boolean isErrorEmail = false;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext contextUser = new WebContext(req, resp, req.getServletContext());
        List<User> listOfUsers = UserService.getInstance().listUsers();
        contextUser.setVariable("UserList", listOfUsers);

        String nameInput = req.getParameter("name");
        String firstNameInput = req.getParameter("firstName");
        String emailInput = req.getParameter("email");
        String passwordInput = req.getParameter("password");
        Part filePart = req.getPart("license");

        InputStream input = filePart.getInputStream();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] license = new byte[10240];
        for (int length = 0; (length = input.read(license)) > 0; ) output.write(license, 0, length);

        User user = new User(nameInput, firstNameInput, emailInput, passwordInput, license);
        try {
            UserService.getInstance().addUser(user);
            isErrorEmail = false;
            resp.sendRedirect("connexion");
        } catch (EmailAlreadyTakenException e) {
            LOGGER.error("L'email que vous utilisez est deja pris par un autre utilisateur", e);
            isErrorEmail = true;
            resp.sendRedirect("sign_up");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if (isErrorEmail) {
            context.setVariable("errorEmail", true);
            isErrorEmail = false;
        } else {
            context.setVariable("errorEmail", false);
        }
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("sign_up", context, resp.getWriter());
    }
}
