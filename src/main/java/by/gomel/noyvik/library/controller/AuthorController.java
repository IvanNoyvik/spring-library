package by.gomel.noyvik.library.controller;

import by.gomel.noyvik.library.exception.ServiceException;
import by.gomel.noyvik.library.model.Author;
import by.gomel.noyvik.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.gomel.noyvik.library.util.constant.ApplicationConstant.*;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("addAuthor")
    public ModelAndView addAuthor(@ModelAttribute Author author) {

        try {
            authorService.addAuthor(author);

        } catch (ServiceException e) {
            return new ModelAndView(REDIRECT_ACTION + ADMIN_JSP, ANSWER, AUTHOR_EXISTS);
        } catch (Exception e) {
            return new ModelAndView(REDIRECT_ACTION + ADMIN_JSP, ANSWER, ADD_AUTHOR_FAIL);
        }
        return new ModelAndView(REDIRECT_ACTION + ADMIN_JSP, ANSWER, ADD_AUTHOR_OK);
    }

}
