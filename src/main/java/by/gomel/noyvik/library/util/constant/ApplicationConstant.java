package by.gomel.noyvik.library.util.constant;

import java.util.Arrays;
import java.util.List;

public interface ApplicationConstant {

    String POSTFIX = ".jsp";
    String PREFIX = "/";
    String TARGET = "target";
    String RESPONSE = "resp";
    String REDIRECT_ACTION = "redirect:/";



    String LOGIN_REGEX = "[a-zA-Z_0-9]{3,40}";
    String PASSWORD_REGEX = "[a-zA-Z0-9]{1,40}";
    //command

    //resp message
    String LOGIN_OK = "Hello ";
    String LOGIN_FAIL = "Wrong log or pass";
    String REGISTRATION_FAIL = "Failed to registration";
    String USER_EXISTS = "User with this login already exists";
    String REGISTRATION_OK = "You have successfully registered!";
    String ADD_ORDER_FAIL = "Book adding error";
    String ADD_ORDER_OK = "The book has been placed in your library";
    String RETURN_ORDER_FAIL = "Book return fail";
    String RETURN_ORDER_OK = "The book is returned to the library";
    String SENT_MESSAGE_OK = "Message sent";
    String SENT_MESSAGE_FAIL = "Sending message error";
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
    String BLOCK = "Your account is blocked";
    String UNKNOWN_COMMAND = "UNKNOWN_COMMAND";
    String ERROR_PROCESS = "Incorrect or unsupported operations";
    String UNKNOWN_OPERATION = "Unknown or unsupported operations";
    String INVALID_DATA = "You entered invalid data";


    //attributes
    // param
    String ID = "id";

    String ROLE_USER = "User";
    String ROLE_ADMIN = "Administrator";
    String USER = "user";
    String USERS = "users";
    String USER_ID = "userId";
    String LOGIN = "login";
    String PASSWORD = "password";
    String NAME = "name";
    String EMAIL = "email";

    String MESSAGES = "messages";

    String BOOKS = "books";
    String BOOK = "book";
    String BOOK_ID = "bookId";
    String HAVE_BOOK = "haveBook";
    String TITLE = "title";
    String GENRE = "genre";
    String GENRES = "genres";
    String AUTHOR = "author";
    String AUTHORS = "authors";
    String QUANTITY = "quantity";
    String IMAGE = "image";
//    String NO_IMAGE = "/static/main/images/no_image.png";
//    String NO_IMAGE = "/../../static/main/images/no_image.png";
    String NO_IMAGE = "/image/no_image.png";
    String DESCRIPTION = "description";

    String ORDERS = "orders";
    String DAYS = "days";
    String CONTEXT = "context";

    String STATUS = "status";
    String OK = "OK";
    String LIMITED = "Limited";
    String LOCKED = "Locked";
    List<String> STATUSES = Arrays.asList(OK, LIMITED, LOCKED);
    String DURATION = "duration";



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
