package by.gomel.noyvik.library.controller.spring;

import by.gomel.noyvik.library.exception.ServiceException;
import by.gomel.noyvik.library.model.Order;
import by.gomel.noyvik.library.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;

@Controller
@RequiredArgsConstructor
public class OrderController {

//    private final BookService bookService;
//    private final UserService userService;
    private final OrderService orderService;
//    private final MessageService messageService;



    @PostMapping(value = "/addOrder")
    public ModelAndView addOrder(@RequestParam("bookId") Long bookId, @RequestParam("userId") Long userId,
                                 @Valid @ModelAttribute Order order, BindingResult br) {


        if (br.hasErrors()) {
//            return setErrorsInModelAndView(br, modelAndView, viewName, MAIN_JSP);
        }

        try {

            Integer duration = order.getDuration();
            orderService.addOrder(bookId, userId, duration);

        } catch (ServiceException e) {
            return new ModelAndView(REDIRECT_ACTION+MAIN_JSP, RESPONSE, ERROR_PROCESS);


        } catch (Exception e) {
            return new ModelAndView(REDIRECT_ACTION+MAIN_JSP, RESPONSE, ADD_ORDER_FAIL);

        }
        return new ModelAndView(REDIRECT_ACTION+PROFILE_JSP, RESPONSE, ADD_ORDER_OK);

    }


    @PostMapping(value = "/returnOrder")
    public ModelAndView returnOrder(@RequestParam("id") Long id, @RequestParam("bookId") Long bookId) {


        try {

            orderService.returnOrder(id, bookId);

        } catch (ServiceException e) {

            return new ModelAndView(REDIRECT_ACTION+MAIN_JSP, RESPONSE, RETURN_ORDER_FAIL);

        }
        return new ModelAndView(REDIRECT_ACTION+MAIN_JSP, RESPONSE, RETURN_ORDER_OK);

    }










// show books image
//        long bookId = Long.parseLong(request.getParameter(BOOK_ID));
//        byte[] image = PROVIDER_SERVICE.getBookService().findImageById(bookId);
//
//        if (image != null) {
//
//            try (ServletOutputStream outputStream = response.getOutputStream()) {
//                outputStream.write(image);
//            }
//
//        } else {
//
//            response.sendRedirect(request.getContextPath() + NO_IMAGE);
//        }
//
//
//    }

}
