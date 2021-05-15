package by.gomel.noyvik.library.util.constant;

public interface ApplicationConstant {

    String POSTFIX = ".jsp";
    String PREFIX = "/WEB-INF/views/";
    String ANSWER = "answer";
    String REDIRECT_ACTION = "redirect:/";


    String LOGIN_REGEX = "[a-zA-Z_0-9]{3,40}";
    String PASSWORD_REGEX = "[a-zA-Z0-9]{1,40}";

    //answer message
    String LOGIN_OK = "Hello ";
    String LOGIN_FAIL = "Wrong log or pass";
    String REGISTRATION_FAIL = "Failed to registration";
    String USER_EXISTS = "User with this login already exists";
    String REGISTRATION_OK = "You have successfully registered!";
    String ADD_ORDER_FAIL = "Book adding error";
    String ADD_ORDER_OK = "The book has been placed in your library";
    String RETURN_ORDER_FAIL = "Book return fail";
    String RETURN_ORDER_OK = "The book is returned to the library";
    String ADD_BOOK_FAIL = "Failed to add book";
    String ADD_BOOK_OK = "Book added";
    String BOOK_EXISTS = "This book already exists";
    String ADD_AUTHOR_FAIL = "Failed to add author";
    String ADD_AUTHOR_OK = "Author added";
    String AUTHOR_EXISTS = "This author already exists";

    String ADD_GENRE_FAIL = "Failed to add genre";
    String ADD_GENRE_OK = "Genre added";
    String GENRE_EXISTS = "This genre already exists";

    String CHANGE_STATUS_FAIL = "Status change error";
    String CHANGE_STATUS_OK = "Status changed";
    String DELETE_USER_FAIL = "Delete user error";
    String DELETE_USER_OK = "User deleted";
    String EDIT_USER_OK = "Information updated";
    String EDIT_USER_FAIL = "Failed to change data";
    String EDIT_BOOK_OK = "Book changed";
    String EDIT_BOOK_FAIL = "Failed to change data";
    String ERROR_PROCESS = "Incorrect or unsupported operations";
    String INVALID_DATA = "You entered invalid data";


    //attributes
    // param
    String ID = "id";

    String ROLE_USER = "User";
    String ROLE_ADMIN = "Administrator";
    String USER = "user";
    String USERS = "users";

    String MESSAGES = "messages";

    String BOOKS = "books";
    String BOOK = "book";
    String HAVE_BOOK = "haveBook";
    String GENRES = "genres";
    String AUTHORS = "authors";
    String ORDERS = "orders";

    String OK = "OK";
    String LIMITED = "Limited";
    String LOCKED = "Locked";


    //JSP
    String MAIN_JSP = "main";
    String REGISTRATION_JSP = "registration";
    String LOGIN_JSP = "login";
    String PROFILE_JSP = "profile";
    String EDIT_USER_JSP = "editUser";
    String BOOK_JSP = "book";
    String BLOCK_JSP = "block";
    String ADMIN_JSP = "admin";
    String EDIT_BOOK_JSP = "editBook";
    String ADD_BOOK_JSP = "addBook";
    String BOOK_CONTENT_JSP = "bookContent";
    String ERROR_JSP = "error";
    String PAGE = "page/";


}
