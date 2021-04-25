package by.gomel.noyvik.library.controller;

import by.gomel.noyvik.library.model.Book;
import by.gomel.noyvik.library.service.BookService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.BOOKS;

@Controller
public class TestController {


    private final BookService bookService;

    public TestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/test")
    protected String bookTest(HttpServletRequest request) {

        List<Book> books = bookService.findAll();

        request.setAttribute(BOOKS, books);
        //        return new ModelAndView("main");
        return ("test");
    }

    @GetMapping(value = "/test", params = "target")
    protected ModelAndView bookTest(@Param("target") String target) {

        List<Book> books = bookService.findAll();
        ModelAndView modelAndView = new ModelAndView(target);
        modelAndView.addObject(BOOKS, books);
        View view = modelAndView.getView();
//        request.setAttribute(BOOKS, books);
        //        return new ModelAndView("main");
        return modelAndView;
    }

}
