//package by.gomel.noyvik.library.controller.commands;
//
//import by.gomel.noyvik.library.controller.FrontCommand;
//import by.gomel.noyvik.library.exception.ServiceException;
//import by.gomel.noyvik.library.model.User;
//import by.gomel.noyvik.library.service.UserService;
//
//import javax.servlet.ServletException;
//import java.io.IOException;
//
//import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;
//
//public class RegistrationCommand extends FrontCommand {
//
//    private final UserService userService = PROVIDER_SERVICE.getUserService();
//
//
//    @Override
//    public void process() throws ServletException, IOException {
//
//        String login = request.getParameter(LOGIN);
//        String password = request.getParameter(PASSWORD);
//        String name = request.getParameter(NAME);
//        String email = request.getParameter(EMAIL);
//
//
//        if (LOGIN_PATTERN.matcher(login).matches() && password.length() > 0 && password.length() <= 40) {
//
//            User user;
//
//            try {
//
//                user = userService.registration(login, password, name, email);
//
//                if (user != null) {
//
//                    redirectWithResp(MAIN_JSP, REGISTRATION_OK);
//
//                } else {
//
//                    redirectWithResp(REGISTRATION_JSP, USER_EXISTS);
//                    return;
//                }
//            } catch (ServiceException e) {
//
//                redirectWithResp(REGISTRATION_JSP, REGISTRATION_FAIL + e.getClass().getSimpleName());
//
//            }
//
//        } else {
//
//            redirectWithResp(REGISTRATION_JSP, REGISTRATION_FAIL + " invalidate data");
//
//        }
//
//
//    }
//}
