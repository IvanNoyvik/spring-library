//package by.gomel.noyvik.library.controller.commands;
//
//import by.gomel.noyvik.library.controller.FrontCommand;
//import by.gomel.noyvik.library.exception.DaoPartException;
//import by.gomel.noyvik.library.exception.ServiceException;
//import by.gomel.noyvik.library.service.AuthorService;
//
//import javax.servlet.ServletException;
//import java.io.IOException;
//
//import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;
//
//public class AddAuthorCommand extends FrontCommand {
//
//    private final AuthorService authorService = PROVIDER_SERVICE.getAuthorService();
//
//
//    @Override
//    public void process() throws ServletException, IOException {
//
//
//        String author = request.getParameter(AUTHOR);
//
//        if (author != null && !author.trim().isEmpty()) {
//
//            try {
//
//                authorService.save(author);
//                redirectWithResp(ADMIN_JSP, ADD_AUTHOR_OK);
//            } catch (ServiceException e) {
//                redirectWithResp(ADMIN_JSP, "This author is exists");
//            } catch (DaoPartException e) {
//                redirectWithResp(ADMIN_JSP, ADD_AUTHOR_FAIL);
//            }
//
//        } else {
//
//            redirectWithResp(ADMIN_JSP, ADD_AUTHOR_FAIL + " invalidate data");
//
//        }
//
//
//    }
//}
