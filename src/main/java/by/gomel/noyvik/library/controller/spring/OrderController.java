package by.gomel.noyvik.library.controller.spring;

import by.gomel.noyvik.library.exception.ServiceException;
import by.gomel.noyvik.library.model.Order;
import by.gomel.noyvik.library.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;



    @PostMapping(value = "/getOrder")
    public ModelAndView addOrder(@Valid @ModelAttribute Order order, BindingResult br) {


        if (br.hasErrors()) {
            List<String> errors = br.getFieldErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
            return new ModelAndView(REDIRECT_ACTION+MAIN_JSP, "errors", errors);
        }

        try {

            orderService.addOrder(order);

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

}
