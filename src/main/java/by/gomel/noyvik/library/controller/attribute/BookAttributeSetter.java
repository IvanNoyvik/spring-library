//package by.gomel.noyvik.library.controller.attribute;
//
//import by.gomel.noyvik.library.model.Book;
//import by.gomel.noyvik.library.model.Order;
//import by.gomel.noyvik.library.model.User;
//import by.gomel.noyvik.library.service.BookService;
//import by.gomel.noyvik.library.service.OrderService;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
//import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;
//
//public class BookAttributeSetter extends AbstractAttributeSetter {
//
//
//    @Override
//    public void setAttribute(HttpServletRequest request) {
//        String resp = request.getParameter("resp");
//        request.setAttribute("resp", resp);
//        BookService bookService = PROVIDER_SERVICE.getBookService();
//        OrderService orderService = PROVIDER_SERVICE.getOrderService();
//
//        long bookId = Long.parseLong(request.getParameter("bookId"));
//        Book book = bookService.findById(bookId);
//        request.setAttribute(BOOK, book);
//
//        List<Order> orders = orderService.findByBookId(bookId);
//        request.setAttribute(ORDERS, orders);
//
//        User user = (User) request.getSession().getAttribute(USER);
//
//        if (user != null) {
//            boolean haveBook = orderService.userHaveBook(bookId, user.getId());
//            request.setAttribute(HAVE_BOOK, haveBook);
//        }
//    }
//}
