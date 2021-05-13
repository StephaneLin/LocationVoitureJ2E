package BLV.servlet;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static BLV.servlet.ConnexionServlet.isUserLogged;

@WebServlet("/sign_out")
public class SignOutServlet extends GenericServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        request.getSession().setAttribute("email", null);
        isUserLogged = false;
        response.sendRedirect("index");
        WebContext context = new WebContext(request, response, request.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(request.getServletContext());
        templateEngine.process("sign_out", context, response.getWriter());
    }
}
