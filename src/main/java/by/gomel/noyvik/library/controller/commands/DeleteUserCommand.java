package by.gomel.noyvik.library.controller.commands;

import by.gomel.noyvik.library.controller.FrontCommand;
import by.gomel.noyvik.library.service.UserService;

import javax.servlet.ServletException;
import java.io.IOException;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;

public class DeleteUserCommand extends FrontCommand {

    private final UserService userService = PROVIDER_SERVICE.getUserService();


    @Override
    public void process() throws ServletException, IOException {

        try {
            long userId = Long.parseLong(request.getParameter(USER_ID));
            userService.deleteById(userId);

            redirectWithResp(ADMIN_JSP, DELETE_USER_OK);

        } catch (Exception e) {

            redirectWithResp(ADMIN_JSP, DELETE_USER_FAIL);

        }


    }
}
