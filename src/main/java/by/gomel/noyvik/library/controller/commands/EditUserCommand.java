//package by.gomel.noyvik.library.controller.commands;
//
//import by.gomel.noyvik.library.controller.FrontCommand;
//import by.gomel.noyvik.library.model.User;
//import by.gomel.noyvik.library.service.UserService;
//
//import javax.servlet.ServletException;
//import java.io.IOException;
//
//import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;
//
//public class EditUserCommand extends FrontCommand {
//
//    private final UserService userService = PROVIDER_SERVICE.getUserService();
//
//
//    @Override
//    public void process() throws ServletException, IOException {
//
//        User user = (User) request.getSession().getAttribute(USER);
//        String name = request.getParameter(NAME);
//        String email = request.getParameter(EMAIL);
//
//        if (user != null) {
//
//
//            user.setName(name);
//            user.setEmail(email);
//
//            user = userService.update(user);
//
//            request.getSession().setAttribute(USER, user);
//
//            redirectWithResp(PROFILE_JSP, EDIT_USER_OK);
//
//        } else {
//
//            redirectWithResp(MAIN_JSP, EDIT_USER_FAIL);
//
//        }
//
//
//    }
//}
