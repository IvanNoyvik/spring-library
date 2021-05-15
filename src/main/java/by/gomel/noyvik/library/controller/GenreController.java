package by.gomel.noyvik.library.controller;

import by.gomel.noyvik.library.exception.ServiceException;
import by.gomel.noyvik.library.model.Genre;
import by.gomel.noyvik.library.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.gomel.noyvik.library.util.constant.ApplicationConstant.*;

@Controller
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping("addGenre")
    public ModelAndView addGenre(@ModelAttribute Genre genre) {

        try {

            genreService.addGenre(genre);

        } catch (ServiceException e) {

            return new ModelAndView(REDIRECT_ACTION + ADMIN_JSP, ANSWER, GENRE_EXISTS);
        } catch (Exception e) {

            return new ModelAndView(REDIRECT_ACTION + ADMIN_JSP, ANSWER, ADD_GENRE_FAIL);
        }

        return new ModelAndView(REDIRECT_ACTION + ADMIN_JSP, ANSWER, ADD_GENRE_OK);
    }
}
