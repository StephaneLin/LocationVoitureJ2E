package BLV.filter;

import BLV.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static BLV.servlet.ConnexionServlet.userRoleLevel;


@WebFilter("/connected/staff/manager/*")
public class ManagerFilter implements Filter {

    private final UserService userService = UserService.getInstance();

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String emailConnectedUser = (String) httpRequest.getSession().getAttribute("email");
        boolean isUserConnected = emailConnectedUser != null;

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (!isUserConnected) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/connexion");
            return;
        }

        if (userRoleLevel < 3) {
            httpResponse.sendRedirect(httpRequest.getRequestURL().toString());
            return;
        }
        chain.doFilter(request, response);
    }
}
