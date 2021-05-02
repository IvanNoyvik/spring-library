//package by.gomel.noyvik.library.controller;
//
//import by.gomel.noyvik.library.controller.commands.UnknownCommand;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;
//
//@WebServlet(name = "FrontControllerServlet", urlPatterns = {"/front"})
//public class FrontControllerServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request,
//                         HttpServletResponse response) throws IOException {
//        doFrontCommand(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        doFrontCommand(request, response);
//    }
//
//    private void doFrontCommand(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        FrontCommand command = getCommand(request);
//        command.init(getServletContext(), request, response);
//        try {
//            command.process();
//        } catch (Exception e) {
//
//            response.sendRedirect(ERROR_PROCESS);
//
//        }
//    }
//
//    private FrontCommand getCommand(HttpServletRequest request) {
//        try {
//            Class type = Class.forName(String.format(COMMAND_PATH,
//                    request.getParameter(COMMAND)));
//            return (FrontCommand) type.asSubclass(FrontCommand.class).newInstance();
//        } catch (Exception e) {
//            return new UnknownCommand();
//        }
//    }
//}
