package by.gomel.noyvik.library.controller.commands;

import by.gomel.noyvik.library.controller.FrontCommand;
import by.gomel.noyvik.library.service.OrderService;

import javax.servlet.ServletException;
import java.io.IOException;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;

public class ReturnOrderCommand extends FrontCommand {

    private final OrderService orderService = PROVIDER_SERVICE.getOrderService();


    @Override
    public void process() throws ServletException, IOException {

        try {

            long id = Long.parseLong(request.getParameter(ID));
            orderService.deleteById(id);

            redirectWithResp(PROFILE_JSP, RETURN_ORDER_OK);

        } catch (Exception e) {

            redirectWithResp(PROFILE_JSP, RETURN_ORDER_FAIL);
        }
    }
}
