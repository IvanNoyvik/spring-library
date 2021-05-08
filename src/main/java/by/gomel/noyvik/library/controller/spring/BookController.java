package by.gomel.noyvik.library.controller.spring;

import by.gomel.noyvik.library.model.Book;
import by.gomel.noyvik.library.model.User;
import by.gomel.noyvik.library.service.BookService;
import by.gomel.noyvik.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final UserService userService;
//    private final OrderService orderService;
//    private final MessageService messageService;


    @PostMapping(value = "/editBook")
    public ModelAndView editBook(@Valid @ModelAttribute Book book, BindingResult br,
                                 @SessionAttribute User user) {


        if (br.hasErrors()) {
//            return setErrorsInModelAndView(br, modelAndView, viewName, EDIT_USER_JSP);
        }

        if (user != null && userService.isAdministrator(user)){

//            book.se
//            bookService.update()
        }

        try {


        } catch (Exception e) {
            return new ModelAndView(REDIRECT_ACTION+EDIT_USER_JSP, RESPONSE, EDIT_USER_FAIL);

        }
        return new ModelAndView(REDIRECT_ACTION+PROFILE_JSP, RESPONSE, EDIT_USER_OK);


    }









// show books image
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
