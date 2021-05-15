package by.gomel.noyvik.library.util.filter;

import by.gomel.noyvik.library.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.gomel.noyvik.library.util.constant.ApplicationConstant.*;

@WebFilter(urlPatterns = {"/getOrder"}, dispatcherTypes = {DispatcherType.REQUEST})
public class LimitedUserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        if (session != null) {

            User user = (User) session.getAttribute(USER);
            if (user != null && user.getStatus().getStatus().equals(LIMITED)) {
                request.setAttribute(ANSWER, "You can't take the book");
                request.getServletContext().getRequestDispatcher("/" + MAIN_JSP).forward(request, response);
            }

        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
