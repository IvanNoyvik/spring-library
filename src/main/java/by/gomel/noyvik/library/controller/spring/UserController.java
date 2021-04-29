package by.gomel.noyvik.library.controller.spring;

import by.gomel.noyvik.library.model.Authenticate;
import by.gomel.noyvik.library.model.Book;
import by.gomel.noyvik.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.BOOKS;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
//    private final OrderService orderService;
//    private final MessageService messageService;


    @PostMapping(value = "/login")
    public ModelAndView login(@Vali@RequestParam Authenticate authenticate) {

        ModelAndView modelAndView = new ModelAndView("main");

        userService.login(authenticate);
        int countPage = pageBooks.getTotalPages();
        List<Book> books = pageBooks.getContent();

        modelAndView.addObject(BOOKS, books);
        modelAndView.addObject("countPage", countPage);

        return modelAndView;
    }


}
