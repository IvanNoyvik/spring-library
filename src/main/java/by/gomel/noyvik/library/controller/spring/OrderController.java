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
public class OrderController extends ModelController {

//    private final BookService bookService;
//    private final UserService userService;
    private final OrderService orderService;
//    private final MessageService messageService;



    @PostMapping(value = "/addOrder")
    public ModelAndView addOrder(@RequestParam("bookId") Long bookId, @RequestParam("userId") Long userId,
                                 @Valid @ModelAttribute Order order, BindingResult br) {

        ModelAndView modelAndView = new ModelAndView();
        String viewName = REDIRECT_ACTION;

        if (br.hasErrors()) {
            return setErrorsInModelAndView(br, modelAndView, viewName, MAIN_JSP);
        }

        try {

            Integer duration = order.getDuration();
            orderService.addOrder(bookId, userId, duration);

        } catch (ServiceException e) {
            return addRespObjectAndViewNameInModelAndView(modelAndView, viewName, ERROR_PROCESS, MAIN_JSP);

        } catch (Exception e) {
            return addRespObjectAndViewNameInModelAndView(modelAndView, viewName, ADD_ORDER_FAIL, MAIN_JSP);
        }

        return addRespObjectAndViewNameInModelAndView(modelAndView, viewName, ADD_ORDER_OK, PROFILE_JSP);
    }


    @PostMapping(value = "/returnOrder")
    public ModelAndView returnOrder(@RequestParam("id") Long id, @RequestParam("bookId") Long bookId) {

        ModelAndView modelAndView = new ModelAndView();
        String viewName = REDIRECT_ACTION;

        try {

            orderService.returnOrder(id, bookId);

        } catch (ServiceException e) {
            return addRespObjectAndViewNameInModelAndView(modelAndView, viewName, RETURN_ORDER_FAIL, MAIN_JSP);
        }

        return addRespObjectAndViewNameInModelAndView(modelAndView, viewName, RETURN_ORDER_OK, MAIN_JSP);
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
