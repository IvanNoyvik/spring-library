//package by.gomel.noyvik.library.controller.attribute;
//
//import by.gomel.noyvik.library.model.Book;
//import by.gomel.noyvik.library.service.BookService;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
//import static by.gomel.noyvik.library.controller.constant.CommandConstant.BOOKS;
//
//public class MainAttributeSetter extends AbstractAttributeSetter {
//
//
//    @Override
//    public void setAttribute(HttpServletRequest request) {
//        String resp = request.getParameter("resp");
//        request.setAttribute("resp", resp);
//        BookService bookService = PROVIDER_SERVICE.getBookService();
//        List<Book> books = bookService.findAll();
//        request.setAttribute(BOOKS, books);
//    }
//}
