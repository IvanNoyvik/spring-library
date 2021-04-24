package by.gomel.noyvik.library.controller.attribute;

import by.gomel.noyvik.library.model.Message;
import by.gomel.noyvik.library.model.Order;
import by.gomel.noyvik.library.model.User;
import by.gomel.noyvik.library.service.MessageService;
import by.gomel.noyvik.library.service.OrderService;
import by.gomel.noyvik.library.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;

public class AdminAttributeSetter extends AbstractAttributeSetter {


    @Override
    public void setAttribute(HttpServletRequest request) {
        String resp = request.getParameter("resp");
        request.setAttribute("resp", resp);
        UserService userService = PROVIDER_SERVICE.getUserService();
        OrderService orderService = PROVIDER_SERVICE.getOrderService();
        MessageService messageService = PROVIDER_SERVICE.getMessageService();

        List<Order> orders = orderService.findAllOverdueOrder();
        request.setAttribute(ORDERS, orders);


        Map<User, Integer> userWithCountOverdueOrder = userService.findUserWithCountOverdueOrder();
        request.setAttribute(USERS, userWithCountOverdueOrder);

        List<Message> messages = messageService.findAll();
        request.setAttribute(MESSAGES, messages);
    }
}
