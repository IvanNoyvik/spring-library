package by.gomel.noyvik.library.controller.spring;

import by.gomel.noyvik.library.model.Book;
import by.gomel.noyvik.library.model.Order;
import by.gomel.noyvik.library.model.User;
import by.gomel.noyvik.library.service.BookService;
import by.gomel.noyvik.library.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final BookService bookService;
//    private final UserService userService;
    private final OrderService orderService;
//    private final MessageService messageService;

    private void setResp(ModelAndView modelAndView, HttpServletRequest request){

        String resp = request.getParameter(RESPONSE);
        if (resp != null){
            modelAndView.addObject("resp", resp);
        }
    }

    @GetMapping(value = "/main/{page}")
    public ModelAndView mainPage(@PathVariable Integer page) {

        ModelAndView modelAndView = new ModelAndView("main");

        Page<Book> pageBooks = bookService.findPageBooks(page);
        int countPage = pageBooks.getTotalPages();
        List<Book> books = pageBooks.getContent();

        modelAndView.addObject(BOOKS, books);
        modelAndView.addObject("countPage", countPage);

        return modelAndView;
    }

    @GetMapping(value = {"/main", "/"})
    public ModelAndView mainPage(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("main");

        Page<Book> pageBooks = bookService.findPageBooks(0);
        int countPage = pageBooks.getTotalPages();
        List<Book> books = pageBooks.getContent();

        modelAndView.addObject(BOOKS, books);
        modelAndView.addObject("countPage", countPage);

        setResp(modelAndView, request);
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView registrationPage(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("registration");

        setResp(modelAndView, request);
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView loginPage(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView("login");

        setResp(modelAndView, request);
        return modelAndView;
    }

    @GetMapping("/profile")
    public ModelAndView profilePage(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView(PROFILE_JSP);
        User user = (User) request.getSession().getAttribute(USER);

        if (user == null){
            modelAndView.addObject(RESPONSE, ERROR_PROCESS);
            modelAndView.setViewName(REDIRECT_ACTION + MAIN_JSP);
            return modelAndView;
        }

        Long userId = user.getId();
        List<Order> orders = orderService.findByUserId(userId);
        modelAndView.addObject(ORDERS, orders);

        setResp(modelAndView, request);
        return modelAndView;
    }



}
