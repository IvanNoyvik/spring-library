package by.gomel.noyvik.library.util.filter;

import by.gomel.noyvik.library.model.Role;
import by.gomel.noyvik.library.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;

@WebFilter(urlPatterns = {"/editBook/*", "/admin", "/addBook"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class AuthorizedFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(USER);
        if (user == null || user.getRoles().stream().map(Role::getRole).noneMatch(s -> s.equals(ROLE_ADMIN))) {
            request.getServletContext().getRequestDispatcher("/" + ERROR_JSP).forward(request, response);
        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
