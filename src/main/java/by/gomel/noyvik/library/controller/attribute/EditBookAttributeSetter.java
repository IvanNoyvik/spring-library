package by.gomel.noyvik.library.controller.attribute;

import by.gomel.noyvik.library.model.Author;
import by.gomel.noyvik.library.model.Book;
import by.gomel.noyvik.library.model.Genre;
import by.gomel.noyvik.library.service.AuthorService;
import by.gomel.noyvik.library.service.BookService;
import by.gomel.noyvik.library.service.GenreService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;

public class EditBookAttributeSetter extends AbstractAttributeSetter {


    @Override
    public void setAttribute(HttpServletRequest request) {
        String resp = request.getParameter("resp");
        request.setAttribute("resp", resp);
        BookService bookService = PROVIDER_SERVICE.getBookService();
        long bookId = Long.parseLong(request.getParameter("bookId"));
        Book book = bookService.findById(bookId);
        request.setAttribute(BOOK, book);
        GenreService genreService = PROVIDER_SERVICE.getGenreService();
        List<Genre> genres = genreService.findAll();
        request.setAttribute(GENRES, genres);
        AuthorService authorService = PROVIDER_SERVICE.getAuthorService();
        List<Author> authors = authorService.findAll();
        request.setAttribute(AUTHORS, authors);
    }
}
