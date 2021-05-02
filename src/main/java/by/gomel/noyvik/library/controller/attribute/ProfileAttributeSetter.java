//package by.gomel.noyvik.library.controller.attribute;
//
//import by.gomel.noyvik.library.model.Order;
//import by.gomel.noyvik.library.model.User;
//import by.gomel.noyvik.library.service.OrderService;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
//import static by.gomel.noyvik.library.controller.constant.CommandConstant.ORDERS;
//import static by.gomel.noyvik.library.controller.constant.CommandConstant.USER;
//
//public class ProfileAttributeSetter extends AbstractAttributeSetter {
//
//
//    @Override
//    public void setAttribute(HttpServletRequest request) {
//        String resp = request.getParameter("resp");
//        request.setAttribute("resp", resp);
//        OrderService orderService = PROVIDER_SERVICE.getOrderService();
//        User user = (User) request.getSession().getAttribute(USER);
//        Long userId = user.getId();
//        List<Order> orders = orderService.findByUserId(userId);
//        request.setAttribute(ORDERS, orders);
//    }
//}
