package by.gomel.noyvik.library.controller.spring;

import by.gomel.noyvik.library.exception.ServiceException;
import by.gomel.noyvik.library.model.Authenticate;
import by.gomel.noyvik.library.model.User;
import by.gomel.noyvik.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;

@Controller
@RequiredArgsConstructor
public class UserController extends ModelController{

    private final UserService userService;

//    private final OrderService orderService;
//    private final MessageService messageService;


    @PostMapping(value = "/registration")
    public ModelAndView registration(@Valid @ModelAttribute User user, BindingResult br) {

        ModelAndView modelAndView = new ModelAndView();
        String viewName = REDIRECT_ACTION;

        if (br.hasErrors()) {

            return setErrorsInModelAndView(br, modelAndView, viewName, REGISTRATION_JSP);

        }

        try {

            userService.createNewUser(user);

        } catch (ServiceException e) {
            return addRespObjectAndViewNameInModelAndView(modelAndView, viewName, USER_EXISTS, REGISTRATION_JSP);

        } catch (Exception e) {
            return addRespObjectAndViewNameInModelAndView(modelAndView, viewName, REGISTRATION_FAIL, REGISTRATION_JSP);
        }

        return addRespObjectAndViewNameInModelAndView(modelAndView, viewName, REGISTRATION_OK, MAIN_JSP);
    }

    @PostMapping(value = "/login")
    public ModelAndView login(@Valid @ModelAttribute Authenticate authenticate, BindingResult br, HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();
        String viewName = REDIRECT_ACTION;

        if (br.hasErrors()) {

            return setErrorsInModelAndView(br, modelAndView, viewName, LOGIN_JSP);

        }

        User user = userService.login(authenticate);

        if (user == null) {
            return addRespObjectAndViewNameInModelAndView(modelAndView, viewName, LOGIN_FAIL, LOGIN_JSP);
        } else {
            session.setAttribute(USER, user);
        }

        return addRespObjectAndViewNameInModelAndView(modelAndView, viewName, LOGIN_OK + user.getName(), MAIN_JSP);
    }


    @GetMapping(value = "/logout")
    public ModelAndView logout(HttpSession session) {

        ModelAndView modelAndView = new ModelAndView(REDIRECT_ACTION + MAIN_JSP);
        session.invalidate();
        return modelAndView;
    }

    @PostMapping(value = "/editUser")
    public ModelAndView editUser(@Valid @ModelAttribute User user, BindingResult br, HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();
        String viewName = REDIRECT_ACTION;

        if (br.hasErrors()) {
            return setErrorsInModelAndView(br, modelAndView, viewName, EDIT_USER_JSP);
        }

        try {

            User userForUpdate = (User) session.getAttribute(USER);
            userForUpdate.setEmail(user.getEmail());
            userForUpdate.setName(user.getName());
            userService.updateUser(userForUpdate);

        } catch (Exception e) {
            return addRespObjectAndViewNameInModelAndView(modelAndView, viewName, EDIT_USER_FAIL, EDIT_USER_JSP);
        }

        return addRespObjectAndViewNameInModelAndView(modelAndView, viewName, EDIT_USER_OK, PROFILE_JSP);

    }




}
