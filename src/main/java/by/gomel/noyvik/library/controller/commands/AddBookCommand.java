//package by.gomel.noyvik.library.controller.commands;
//
//import by.gomel.noyvik.library.controller.FrontCommand;
//import by.gomel.noyvik.library.model.Book;
//import by.gomel.noyvik.library.service.BookService;
//
//import javax.servlet.ServletException;
//import java.io.IOException;
//
//import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;
//
//public class AddBookCommand extends FrontCommand {
//
//    private final BookService bookService = PROVIDER_SERVICE.getBookService();
//
//
//    @Override
//    public void process() throws ServletException, IOException {
//
//        String title = request.getParameter(TITLE);
//        String description = request.getParameter(DESCRIPTION);
//
//        int quantity;
//
//        try {
//            quantity = Integer.parseInt(request.getParameter(QUANTITY));
//
//        } catch (NumberFormatException e) {
//
//            redirectWithResp(MAIN_JSP, PARSE_NUMBER_EXCEPTION);
//            return;
//        }
//
//        String[] genres = request.getParameterValues(GENRES);
//        String author = request.getParameter(AUTHOR);
//
//        if (quantity >= 0 && quantity <= 180 && title != null && !title.trim().isEmpty() && genres != null) {
//
//            try {
//
//                Book book = bookService.save(title, description, quantity, genres, author);
//
//                if (book != null) {
//
//                    redirectWithResp(MAIN_JSP, ADD_BOOK_OK);
//                } else {
//                    redirectWithResp(MAIN_JSP, BOOK_EXISTS);
//
//                }
//
//            } catch (SecurityException e) {
//
//                redirectWithResp(MAIN_JSP, ADD_BOOK_FAIL);
//
//            }
//
//        } else {
//
//            redirectWithResp(MAIN_JSP, ADD_BOOK_FAIL + " invalidate data");
//
//        }
//
//    }
//}
