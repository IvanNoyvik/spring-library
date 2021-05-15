package by.gomel.noyvik.library.util.listener;

import by.gomel.noyvik.library.model.Role;
import by.gomel.noyvik.library.util.CurrentDate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static by.gomel.noyvik.library.util.constant.ApplicationConstant.ROLE_ADMIN;


@WebListener
public class ApplicationStartUpListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        sce.getServletContext().setAttribute("now", new CurrentDate());
        sce.getServletContext().setAttribute("admin", new Role(ROLE_ADMIN));

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
