//package by.gomel.noyvik.library.controller.commands;
//
//import by.gomel.noyvik.library.controller.FrontCommand;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;
//
//public class LogoutCommand extends FrontCommand {
//
//
//    @Override
//    public void process() throws ServletException, IOException {
//
//        HttpSession session = request.getSession(false);
//
//        if (session != null) {
//            session.invalidate();
//        }
//
//        redirectWithResp(MAIN_JSP, "");
//
//    }
//}
