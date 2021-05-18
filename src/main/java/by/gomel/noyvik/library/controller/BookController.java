package by.gomel.noyvik.library.controller;

import by.gomel.noyvik.library.exception.ServiceException;
import by.gomel.noyvik.library.model.Book;
import by.gomel.noyvik.library.model.User;
import by.gomel.noyvik.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static by.gomel.noyvik.library.util.constant.ApplicationConstant.*;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @PostMapping(value = "/editBook")
    public ModelAndView editBook(@Valid @ModelAttribute Book book, BindingResult br,
                                 @SessionAttribute User user) {


        if (br.hasErrors()) {
            List<String> errors = br.getFieldErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
            return new ModelAndView(REDIRECT_ACTION + EDIT_BOOK_JSP, "errors", errors);
        }

        if (user != null && user.isAdministrator()) {

            try {

                bookService.update(book);

            } catch (Exception e) {

                return new ModelAndView(REDIRECT_ACTION + BOOK_JSP + "/" + book.getId(), ANSWER, EDIT_BOOK_FAIL);
            }
            return new ModelAndView(REDIRECT_ACTION + BOOK_JSP + "/" + book.getId(), ANSWER, EDIT_BOOK_OK);
        }

        return new ModelAndView(REDIRECT_ACTION + MAIN_JSP, ANSWER, ERROR_PROCESS);
    }


    @PostMapping("/addImage")
    public ModelAndView addImage(@RequestParam Long id, @RequestParam("image") MultipartFile image) {

        try {

            bookService.addImage(id, image);
        } catch (ServiceException e) {
            return new ModelAndView(REDIRECT_ACTION + EDIT_BOOK_JSP + "/" + id, ANSWER, EDIT_BOOK_FAIL);
        }

        return new ModelAndView(REDIRECT_ACTION + EDIT_BOOK_JSP + "/" + id, ANSWER, EDIT_BOOK_OK);
    }


    @GetMapping("/getImage/{id}")
    public @ResponseBody
    byte[] getImage(@PathVariable Long id) {

        return bookService.findImageById(id);

    }

    @PostMapping(value = "/addBook")
    public ModelAndView addBook(@Valid @ModelAttribute Book book, BindingResult br,
                                @SessionAttribute User user) {

        if (br.hasErrors()) {
            List<String> errors = br.getFieldErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
            return new ModelAndView(REDIRECT_ACTION + ADD_BOOK_JSP, "errors", errors);
        }

        if (user != null && user.isAdministrator()) {

            try {
                bookService.save(book);
            } catch (Exception e) {

                return new ModelAndView(REDIRECT_ACTION + BOOK_JSP + "/" + book.getId(), ANSWER, ADD_BOOK_FAIL);
            }

            return new ModelAndView(REDIRECT_ACTION + BOOK_JSP + "/" + book.getId(), ANSWER, ADD_BOOK_OK);
        }

        return new ModelAndView(REDIRECT_ACTION + MAIN_JSP, ANSWER, ERROR_PROCESS);
    }
}
