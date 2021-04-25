//package by.gomel.noyvik.library.controller.commands;
//
//import by.gomel.noyvik.library.controller.FrontCommand;
//
//import javax.servlet.ServletException;
//import javax.servlet.ServletOutputStream;
//import java.io.IOException;
//
//import static by.gomel.noyvik.library.controller.constant.CommandConstant.BOOK_ID;
//import static by.gomel.noyvik.library.controller.constant.CommandConstant.NO_IMAGE;
//
//public class GetImageCommand extends FrontCommand {
//
//
//    @Override
//    public void process() throws ServletException, IOException {
//
//        long bookId = Long.parseLong(request.getParameter(BOOK_ID));
//        byte[] image = PROVIDER_SERVICE.getBookService().findImageById(bookId);
//
//        if (image != null) {
//
//            try (ServletOutputStream outputStream = response.getOutputStream()) {
//                outputStream.write(image);
//            }
//
//        } else {
//
//            response.sendRedirect(request.getContextPath() + NO_IMAGE);
//        }
//
//
//    }
//}
