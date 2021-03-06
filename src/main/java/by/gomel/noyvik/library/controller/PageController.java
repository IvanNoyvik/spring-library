package by.gomel.noyvik.library.controller;

import by.gomel.noyvik.library.model.*;
import by.gomel.noyvik.library.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.gomel.noyvik.library.util.constant.ApplicationConstant.*;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final BookService bookService;
    private final UserService userService;
    private final OrderService orderService;
    private final MessageService messageService;
    private final GenreService genreService;
    private final AuthorService authorService;

    private void setResp(ModelAndView modelAndView, HttpServletRequest request) {

        String answer = request.getParameter(ANSWER);
        String errors = request.getParameter("errors");
        if (answer != null) {
            modelAndView.addObject(ANSWER, answer);
        }
        if (errors != null) {
            modelAndView.addObject("errors", errors);
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

        ModelAndView modelAndView = new ModelAndView(MAIN_JSP);

        Page<Book> pageBooks = bookService.findPageBooks(0);
        int countPage = pageBooks.getTotalPages();
        List<Book> books = pageBooks.getContent();

        modelAndView.addObject(BOOKS, books);
        modelAndView.addObject("countPage", countPage);

        setResp(modelAndView, request);
        return modelAndView;
    }

    @GetMapping("/page/{jspPage}")
    public ModelAndView userAuthenticatePages(HttpServletRequest request, @PathVariable String jspPage) {

        ModelAndView modelAndView = new ModelAndView(jspPage);

        setResp(modelAndView, request);
        return modelAndView;
    }

    @GetMapping("/profile")
    public ModelAndView profilePage(@SessionAttribute User user, HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView(PROFILE_JSP);

        if (user == null) {
            modelAndView.addObject(ANSWER, ERROR_PROCESS);
            modelAndView.setViewName(REDIRECT_ACTION + MAIN_JSP);
            return modelAndView;
        }

        Long userId = user.getId();
        List<Order> orders = orderService.findByUserId(userId);
        modelAndView.addObject(ORDERS, orders);

        List<Message> messages = messageService.findAllByUserId(userId);
        request.setAttribute(MESSAGES, messages);

        setResp(modelAndView, request);
        return modelAndView;
    }


    @GetMapping("/book/{bookId}")
    public ModelAndView bookPage(HttpServletRequest request, @PathVariable Long bookId) {

        ModelAndView modelAndView = new ModelAndView(BOOK_JSP);

        Book book = bookService.findBookById(bookId);
        request.setAttribute(BOOK, book);

        User user = (User) request.getSession().getAttribute(USER);

        if (user != null) {
            boolean haveBook = orderService.userHaveBook(bookId, user.getId());
            request.setAttribute(HAVE_BOOK, haveBook);

            if (user.isAdministrator()) {
                List<Order> orders = orderService.findByBookId(bookId);
                request.setAttribute(ORDERS, orders);

            }

        }

        setResp(modelAndView, request);
        return modelAndView;
    }

    @GetMapping("/bookContent/{bookId}")
    public ModelAndView bookContentPage(HttpServletRequest request, @PathVariable Long bookId) {

        ModelAndView modelAndView = new ModelAndView(BOOK_CONTENT_JSP);

        Book book = bookService.findBookById(bookId);
        request.setAttribute(BOOK, book);

        setResp(modelAndView, request);
        return modelAndView;
    }


    @GetMapping("/editBook/{bookId}")
    public ModelAndView editBookPage(HttpServletRequest request, @PathVariable Long bookId) {

        ModelAndView modelAndView = new ModelAndView(EDIT_BOOK_JSP);

        Book book = bookService.findBookById(bookId);
        request.setAttribute(BOOK, book);
        List<Genre> genres = genreService.findAll();
        request.setAttribute(GENRES, genres);
        List<Author> authors = authorService.findAll();
        request.setAttribute(AUTHORS, authors);

        setResp(modelAndView, request);
        return modelAndView;
    }

    @GetMapping("/addBook")
    public ModelAndView addBookPage(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView(ADD_BOOK_JSP);

        List<Genre> genres = genreService.findAll();
        request.setAttribute(GENRES, genres);
        List<Author> authors = authorService.findAll();
        request.setAttribute(AUTHORS, authors);

        setResp(modelAndView, request);
        return modelAndView;
    }


    @GetMapping("/admin")
    public ModelAndView adminPage(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView(ADMIN_JSP);

        User user = (User) request.getSession().getAttribute(USER);
        if (user != null && user.isAdministrator()) {

            List<Order> orders = orderService.findAllOverdueOrder();
            request.setAttribute(ORDERS, orders);

            List<User> usersWithOrder = userService.findAllUserWithOrder();
            request.setAttribute(USERS, usersWithOrder);

            List<Message> messages = messageService.findAll();
            request.setAttribute(MESSAGES, messages);

            setResp(modelAndView, request);
            return modelAndView;
        } else {

            return new ModelAndView(MAIN_JSP, ANSWER, ERROR_PROCESS);
        }

    }

    @GetMapping("/block")
    public ModelAndView blockPages(@SessionAttribute User user) {
        if (user != null && user.getStatus().getStatus().equals(LOCKED)){

            List<Message> messages = messageService.findAllByUserId(user.getId());
            return new ModelAndView(BLOCK_JSP, MESSAGES, messages);
        }
        return new ModelAndView(ERROR_JSP);

    }

    @GetMapping("/error")
    public ModelAndView errorPages() {
        return new ModelAndView(ERROR_JSP);
    }

}
