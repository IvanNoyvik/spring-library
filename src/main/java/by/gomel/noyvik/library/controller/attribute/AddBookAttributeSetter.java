package by.gomel.noyvik.library.controller.attribute;

import by.gomel.noyvik.library.model.Author;
import by.gomel.noyvik.library.model.Genre;
import by.gomel.noyvik.library.service.AuthorService;
import by.gomel.noyvik.library.service.GenreService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.AUTHORS;
import static by.gomel.noyvik.library.controller.constant.CommandConstant.GENRES;

public class AddBookAttributeSetter extends AbstractAttributeSetter {


    @Override
    public void setAttribute(HttpServletRequest request) {
        String resp = request.getParameter("resp");
        request.setAttribute("resp", resp);
        GenreService genreService = PROVIDER_SERVICE.getGenreService();
        List<Genre> genres = genreService.findAll();
        request.setAttribute(GENRES, genres);
        AuthorService authorService = PROVIDER_SERVICE.getAuthorService();
        List<Author> authors = authorService.findAll();
        request.setAttribute(AUTHORS, authors);
    }
}
