package by.gomel.noyvik.library.util.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.ERROR_JSP;

@WebFilter(urlPatterns = "/*", dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR})
public class ExceptionsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/" + ERROR_JSP).forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
