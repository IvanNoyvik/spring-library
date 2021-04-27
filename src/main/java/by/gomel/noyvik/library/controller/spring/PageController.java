package by.gomel.noyvik.library.controller.spring;

import by.gomel.noyvik.library.model.Book;
import by.gomel.noyvik.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.BOOKS;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final BookService bookService;
//    private final UserService userService;
//    private final OrderService orderService;
//    private final MessageService messageService;


    @GetMapping("/main123")
    protected String mainPage(HttpServletRequest request) {
//todo
        List<Book> books = bookService.findAll();

        request.setAttribute(BOOKS, books);
        //        return new ModelAndView("main");
        return ("test");
    }

//    @GetMapping(value = "/test", params = "target")
//    protected ModelAndView bookTest(@Param("target") String target) {
//
//        List<Book> books = bookService.findAll();
//        ModelAndView modelAndView = new ModelAndView(target);
//        modelAndView.addObject(BOOKS, books);
////        request.setAttribute(BOOKS, books);
//        //        return new ModelAndView("main");
//        return modelAndView;
//    }

}
