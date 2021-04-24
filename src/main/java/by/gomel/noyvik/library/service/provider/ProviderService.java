package by.gomel.noyvik.library.service.provider;

import by.gomel.noyvik.library.service.*;
import by.gomel.noyvik.library.service.impl.*;

public final class ProviderService {

    private static final ProviderService INSTANCE = new ProviderService();

    private final BookService bookService = new BookServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final MessageService messageService = new MessageServiceImpl();
    private final AuthorService authorService = new AuthorServiceImpl();
    private final GenreService genreService = new GenreServiceImpl();


    private ProviderService() {
    }

    public static ProviderService getInstance() {
        return INSTANCE;
    }


    public BookService getBookService() {
        return bookService;
    }

    public UserService getUserService() {
        return userService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public GenreService getGenreService() {
        return genreService;
    }

    public AuthorService getAuthorService() {
        return authorService;
    }
}
