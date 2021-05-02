//package by.gomel.noyvik.library.controller.commands;
//
//import by.gomel.noyvik.library.controller.FrontCommand;
//import by.gomel.noyvik.library.service.UserService;
//
//import javax.servlet.ServletException;
//import java.io.IOException;
//
//import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;
//
//public class ChangeStatusCommand extends FrontCommand {
//
//    private final UserService userService = PROVIDER_SERVICE.getUserService();
//
//
//    @Override
//    public void process() throws ServletException, IOException {
//
//        try {
//
//            long userId = Long.parseLong(request.getParameter(USER_ID));
//            String status = request.getParameter(STATUS);
//
//            String durationStr = request.getParameter(DURATION);
//
//            int duration = (durationStr != null && durationStr.matches("/d+"))
//                    ? Integer.parseInt(durationStr) : 0;
//
//            if (STATUSES.contains(status)) {
//
//                if (userService.changeStatus(userId, status, duration)) {
//
//                    redirectWithResp(ADMIN_JSP, CHANGE_STATUS_OK);
//
//                } else {
//
//                    redirectWithResp(ADMIN_JSP, CHANGE_STATUS_FAIL + " data exception");
//
//                }
//
//            } else {
//
//                redirectWithResp(ADMIN_JSP, UNKNOWN_OPERATION);
//
//            }
//
//
//        } catch (SecurityException e) {
//
//            redirectWithResp(ADMIN_JSP, CHANGE_STATUS_FAIL + " - service fail");
//
//        }
//
//
//    }
//}
