package by.gomel.noyvik.library.controller.spring;

import by.gomel.noyvik.library.model.Authenticate;
import by.gomel.noyvik.library.model.User;
import by.gomel.noyvik.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    private final OrderService orderService;
//    private final MessageService messageService;

    @Autowired
    private MessageSource messageSource;

    @PostMapping(value = "/registration")
    public ModelAndView registration(@Valid @RequestParam User user, BindingResult br) {

        if (br.hasErrors()) {

            return new ModelAndView("redirect:/main");

        }

        userService.createNewUser(user);


        return new ModelAndView("redirect:/login");
    }

    @PostMapping(value = "/login")
    public ModelAndView login(@Valid @ModelAttribute Authenticate authenticate, BindingResult br, HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();
        String viewName = REDIRECT_ACTION;

        if (br.hasErrors()) {
            List<FieldError> fieldErrors = br.getFieldErrors();
            String resp = "";
            for (ObjectError error : fieldErrors) {

                resp += messageSource.getMessage(error, null) + "\n";
            }

            modelAndView.addObject(RESPONSE, resp);
            viewName += LOGIN_JSP;
            modelAndView.setViewName(viewName);
            return modelAndView;

        }

        User user = userService.login(authenticate);

        if (user == null) {
            modelAndView.addObject(RESPONSE, LOGIN_FAIL);
            viewName += LOGIN_JSP;
            modelAndView.setViewName(viewName);
            return modelAndView;
        } else {
            session.setAttribute(USER, user);
        }

        modelAndView.addObject(RESPONSE, LOGIN_OK + user.getName());
        viewName += MAIN_JSP;
        modelAndView.setViewName(viewName);
        return modelAndView;
    }


    @GetMapping(value = "/logout")
    public ModelAndView logout(HttpSession session) {

        ModelAndView modelAndView = new ModelAndView(REDIRECT_ACTION + MAIN_JSP);
        session.invalidate();
        return modelAndView;
    }


}
