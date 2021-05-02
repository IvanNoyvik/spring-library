//package by.gomel.noyvik.library.util.listener;
//
//import by.gomel.noyvik.library.persistence.repository.RoleRepository;
//import by.gomel.noyvik.library.util.CurrentDate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//import static by.gomel.noyvik.library.controller.constant.CommandConstant.ROLE_ADMIN;
//
//;
//
//@WebListener
//@Component
//public class ApplicationStartUpListener implements ServletContextListener {
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//
//        sce.getServletContext().setAttribute("now", new CurrentDate());
//        sce.getServletContext().setAttribute("admin", roleRepository.findByRole(ROLE_ADMIN));
//
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//    }
//}