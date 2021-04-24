package by.gomel.noyvik.library.controller.commands;

import by.gomel.noyvik.library.controller.FrontCommand;
import by.gomel.noyvik.library.model.User;
import by.gomel.noyvik.library.service.OrderService;

import javax.servlet.ServletException;
import java.io.IOException;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;

public class AddOrderCommand extends FrontCommand {

    private final OrderService orderService = PROVIDER_SERVICE.getOrderService();


    @Override
    public void process() throws ServletException, IOException {

        int duration;
        long bookId;

        try {
            bookId = Long.parseLong(request.getParameter(BOOK_ID));
            duration = Integer.parseInt(request.getParameter(DAYS));

        } catch (NumberFormatException e) {

            redirectWithResp(MAIN_JSP, PARSE_NUMBER_EXCEPTION);
            return;
        }

        User user = (User) request.getSession().getAttribute(USER);


        if (user.getStatus().getStatus().equalsIgnoreCase("OK") && duration <= 180) {

            try {

                orderService.addOrder(user, bookId, duration);
            } catch (Exception e) {

                redirectWithResp(MAIN_JSP, ADD_ORDER_FAIL);
                return;

            }

            redirectWithResp(PROFILE_JSP, ADD_ORDER_OK);

        } else {

            redirectWithResp(MAIN_JSP, ADD_ORDER_FAIL);

        }


    }
}
