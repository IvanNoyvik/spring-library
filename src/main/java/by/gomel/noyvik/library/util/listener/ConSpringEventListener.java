//package by.gomel.noyvik.library.util.listener;
//
//import by.gomel.noyvik.library.persistence.repository.RoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ConSpringEventListener implements ApplicationListener<ContextRefreshedEvent> {
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        System.out.println();
//        event.getApplicationContext().
////        sce.getServletContext().setAttribute("now", new CurrentDate());
////        sce.getServletContext().setAttribute("admin", roleRepository.findByRole(ROLE_ADMIN));
//    }
//}
