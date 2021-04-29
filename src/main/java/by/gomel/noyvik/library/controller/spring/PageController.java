package by.gomel.noyvik.library.controller.spring;

import by.gomel.noyvik.library.model.Book;
import by.gomel.noyvik.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.BOOKS;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final BookService bookService;
//    private final UserService userService;
//    private final OrderService orderService;
//    private final MessageService messageService;

    private void setResp(ModelAndView modelAndView, String resp){

        if (resp != null){
            modelAndView.addObject("resp", resp);
        }
    }

    @GetMapping(value = {"/main", "/"}, params = "page")
    public ModelAndView mainPage(@RequestParam Integer page) {

        ModelAndView modelAndView = new ModelAndView("main");

        Page<Book> pageBooks = bookService.findPageBooks(page);
        int countPage = pageBooks.getTotalPages();
        List<Book> books = pageBooks.getContent();

        modelAndView.addObject(BOOKS, books);
        modelAndView.addObject("countPage", countPage);

        return modelAndView;
    }

    @GetMapping(value = {"/main", "/"})
    public ModelAndView mainPage() {

        ModelAndView modelAndView = new ModelAndView("main");

        Page<Book> pageBooks = bookService.findPageBooks(0);
        int countPage = pageBooks.getTotalPages();
        List<Book> books = pageBooks.getContent();

        modelAndView.addObject(BOOKS, books);
        modelAndView.addObject("countPage", countPage);

        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView registrationPage() {

        ModelAndView modelAndView = new ModelAndView("registration");

        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {

        ModelAndView modelAndView = new ModelAndView("login");

        return modelAndView;
    }



}
