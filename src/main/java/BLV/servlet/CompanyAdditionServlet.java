package BLV.servlet;

import BLV.entity.User;
import BLV.exception.EmailAlreadyTakenException;
import BLV.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/connected/staff/manager/company_addition")
public class CompanyAdditionServlet extends GenericServlet {

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

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("connected/staff/manager/company_addition", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String companyName = req.getParameter("companyName");
        int siret = Integer.parseInt(req.getParameter("siret"));
        int companyPhone = Integer.parseInt(req.getParameter("companyPhone"));
        String emailInput = req.getParameter("email");
        String passwordInput = req.getParameter("password");

        User user = new User(companyName, siret, companyPhone, emailInput, passwordInput);
        try {
            UserService.getInstance().addUser(user);
            resp.sendRedirect("crud_tool");
        } catch (EmailAlreadyTakenException e) {
            resp.sendRedirect("user_addition");
        }
    }
}
