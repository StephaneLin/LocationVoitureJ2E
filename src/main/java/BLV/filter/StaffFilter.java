package BLV.filter;

import BLV.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static BLV.servlet.ConnexionServlet.userRoleLevel;


@WebFilter("/connected/staff/*")
public class StaffFilter implements Filter {

    private final UserService userService = UserService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String emailConnectedUser = (String) httpRequest.getSession().getAttribute("email");
        boolean isUserConnected = emailConnectedUser != null;

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (!isUserConnected) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/connexion");
            return;
        }

        if (userRoleLevel < 2) {
            httpResponse.sendRedirect(httpRequest.getRequestURL().toString());
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
