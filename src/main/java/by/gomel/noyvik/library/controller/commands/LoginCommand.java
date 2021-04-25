//package by.gomel.noyvik.library.controller.commands;
//
//import by.gomel.noyvik.library.controller.FrontCommand;
//import by.gomel.noyvik.library.exception.ServiceException;
//import by.gomel.noyvik.library.model.User;
//
//import javax.servlet.ServletException;
//import java.io.IOException;
//
//import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;
//
//public class LoginCommand extends FrontCommand {
//
//
//    @Override
//    public void process() throws ServletException, IOException {
//
//        String login = request.getParameter(LOGIN);
//        String password = request.getParameter(PASSWORD);
//        User user = null;
//
//        if (!login.isEmpty() && !password.isEmpty()) {
//
//            try {
//
//                user = PROVIDER_SERVICE.getUserService().findByLoginAndPassword(login, password);
//
//            } catch (ServiceException e) {
//
//                redirectWithResp(LOGIN_JSP, LOGIN_FAIL + e.getClass().getSimpleName());
//                return;
//            }
//
//        }
//
//
//        if (user != null) {
//
//            request.getSession().setAttribute(USER, user);
//
//            if (!user.getStatus().getStatus().equalsIgnoreCase(LOCKED)) {
//
//                redirectWithResp(MAIN_JSP, LOGIN_OK + user.getName() + "!");
//
//            } else {
//
//                redirectWithResp(BLOCK_JSP, BLOCK);
//            }
//
//        } else {
//
//            redirectWithResp(LOGIN_JSP, LOGIN_FAIL);
//
//        }
//
//
//    }
//}
