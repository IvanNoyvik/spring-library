package by.gomel.noyvik.library.controller.spring;

import by.gomel.noyvik.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
//    private final UserService userService;
//    private final OrderService orderService;
//    private final MessageService messageService;




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

}
