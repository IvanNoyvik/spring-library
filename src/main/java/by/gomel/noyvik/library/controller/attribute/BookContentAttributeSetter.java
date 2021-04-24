package by.gomel.noyvik.library.controller.attribute;

import by.gomel.noyvik.library.model.Book;
import by.gomel.noyvik.library.service.BookService;

import javax.servlet.http.HttpServletRequest;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.BOOK;

public class BookContentAttributeSetter extends AbstractAttributeSetter {


    @Override
    public void setAttribute(HttpServletRequest request) {
        String resp = request.getParameter("resp");
        request.setAttribute("resp", resp);
        BookService bookService = PROVIDER_SERVICE.getBookService();
        long bookId = Long.parseLong(request.getParameter("bookId"));
        Book book = bookService.findById(bookId);
        request.setAttribute(BOOK, book);
    }
}
