//package by.gomel.noyvik.library.controller.commands;
//
//import by.gomel.noyvik.library.controller.FrontCommand;
//import by.gomel.noyvik.library.exception.DaoPartException;
//import by.gomel.noyvik.library.exception.ServiceException;
//import by.gomel.noyvik.library.service.GenreService;
//
//import javax.servlet.ServletException;
//import java.io.IOException;
//
//import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;
//
//public class AddGenreCommand extends FrontCommand {
//
//    private final GenreService genreService = PROVIDER_SERVICE.getGenreService();
//
//
//    @Override
//    public void process() throws ServletException, IOException {
//
//
//        String genre = request.getParameter(GENRE);
//
//        if (genre != null && !genre.trim().isEmpty()) {
//
//            try {
//
//                genreService.save(genre);
//                redirectWithResp(ADMIN_JSP, ADD_GENRE_OK);
//            } catch (ServiceException e) {
//                redirectWithResp(ADMIN_JSP, "This genre is exists");
//            } catch (DaoPartException e) {
//                redirectWithResp(ADMIN_JSP, ADD_GENRE_FAIL);
//            }
//
//        } else {
//
//            redirectWithResp(ADMIN_JSP, ADD_GENRE_FAIL + " invalidate data");
//
//        }
//
//
//    }
//}
