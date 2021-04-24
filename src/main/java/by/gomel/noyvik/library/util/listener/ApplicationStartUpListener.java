package by.gomel.noyvik.library.util.listener;

import by.gomel.noyvik.library.persistance.connection.ProviderDao;
import by.gomel.noyvik.library.persistance.dao.RoleDao;
import by.gomel.noyvik.library.util.CurrentDate;
import org.h2.tools.Server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

;

@WebListener
public class ApplicationStartUpListener implements ServletContextListener {

    private static Server SERVER;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            SERVER = Server.createTcpServer().start();
        } catch (SQLException e) {
            throw new RuntimeException("Failed start tcp H2 server");
        }


        RoleDao roleDao = ProviderDao.getInstance().getRoleDao();


        sce.getServletContext().setAttribute("now", new CurrentDate());
        sce.getServletContext().setAttribute("admin", roleDao.getAdminRole());

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
