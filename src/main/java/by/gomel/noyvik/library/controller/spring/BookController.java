package by.gomel.noyvik.library.controller.spring;

import by.gomel.noyvik.library.model.Book;
import by.gomel.noyvik.library.model.User;
import by.gomel.noyvik.library.service.BookService;
import by.gomel.noyvik.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final UserService userService;
//    private final OrderService orderService;
//    private final MessageService messageService;


    @PostMapping(value = "/editBook")
    public ModelAndView editBook(@Valid @ModelAttribute Book book, BindingResult br,
                                 @SessionAttribute User user) {


        if (br.hasErrors()) {
            List<String> errors = br.getFieldErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
            return new ModelAndView(EDIT_BOOK_JSP, "errors", errors);
        }

        if (user != null && userService.isAdministrator(user)) {

            try {

                bookService.update(book);

            } catch (Exception e) {

                return new ModelAndView(REDIRECT_ACTION + BOOK_JSP + "/" + book.getId(), RESPONSE, EDIT_BOOK_FAIL);

            }

            return new ModelAndView(REDIRECT_ACTION + BOOK_JSP + "/" + book.getId(), RESPONSE, EDIT_BOOK_OK);

        }


        return new ModelAndView(REDIRECT_ACTION + MAIN_JSP, RESPONSE, ERROR_PROCESS);


    }


    @PostMapping("/addImage")
    public ModelAndView addImage(@RequestParam Long id, @RequestParam("image") MultipartFile image) {

        bookService.addImage(id, image);

        return new ModelAndView(REDIRECT_ACTION + EDIT_BOOK_JSP + "/" + id, RESPONSE, EDIT_BOOK_OK);

    }


    @GetMapping("/getImage/{id}")
    public @ResponseBody byte[] getImage(@PathVariable Long id) throws IOException {

        return bookService.findImageById(id);

    }

}
