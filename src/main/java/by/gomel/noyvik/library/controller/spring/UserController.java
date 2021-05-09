package by.gomel.noyvik.library.controller.spring;

import by.gomel.noyvik.library.exception.LockedLoginException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;

@Controller
@RequiredArgsConstructor
public class UserController extends ModelController {

    private final UserService userService;

//    private final OrderService orderService;
//    private final MessageService messageService;


    @PostMapping(value = "/registration")
    public ModelAndView registration(@Valid @ModelAttribute User user, BindingResult br) {

        if (br.hasErrors()) {

//            new ModelAndView(REDIRECT_ACTION+REGISTRATION_JSP, "error", br);
            return new ModelAndView(REDIRECT_ACTION + REGISTRATION_JSP, "error", br);

        }
        try {

            userService.createNewUser(user);

        } catch (ServiceException e) {

            return new ModelAndView(REDIRECT_ACTION + REGISTRATION_JSP, RESPONSE, USER_EXISTS);


        } catch (Exception e) {
            return new ModelAndView(REDIRECT_ACTION + REGISTRATION_JSP, RESPONSE, REGISTRATION_FAIL);

        }
        return new ModelAndView(REDIRECT_ACTION + MAIN_JSP, RESPONSE, REGISTRATION_OK);

    }

    @PostMapping(value = "/login")
    public ModelAndView login(@Valid @ModelAttribute Authenticate authenticate, BindingResult br, HttpSession session) {


        if (br.hasErrors()) {

//todo return view validation
        }

        try {

            User user = userService.login(authenticate);

            if (user == null) {

                return new ModelAndView(REDIRECT_ACTION + LOGIN_JSP, RESPONSE, LOGIN_FAIL);

            } else {
                session.setAttribute(USER, user);
            }
            return new ModelAndView(REDIRECT_ACTION + MAIN_JSP, RESPONSE, LOGIN_OK + user.getName());

        } catch (LockedLoginException e) {
            return new ModelAndView(REDIRECT_ACTION + BLOCK_JSP);

        }

    }


    @GetMapping(value = "/logout")
    public ModelAndView logout(HttpSession session) {

        ModelAndView modelAndView = new ModelAndView(REDIRECT_ACTION + MAIN_JSP);
        session.invalidate();
        return modelAndView;
    }

    @PostMapping(value = "/editUser")
    public ModelAndView editUser(@Valid @ModelAttribute User user, BindingResult br, HttpSession session) {


        if (br.hasErrors()) {
//            return setErrorsInModelAndView(br, modelAndView, viewName, EDIT_USER_JSP);
        }

        try {

            User userForUpdate = (User) session.getAttribute(USER);
            userForUpdate.setEmail(user.getEmail());
            userForUpdate.setName(user.getName());
            userService.updateUser(userForUpdate);

        } catch (Exception e) {
            return new ModelAndView(REDIRECT_ACTION + EDIT_USER_JSP, RESPONSE, EDIT_USER_FAIL);

        }
        return new ModelAndView(REDIRECT_ACTION + PROFILE_JSP, RESPONSE, EDIT_USER_OK);


    }

    @PostMapping(value = "/deleteUser")
    public ModelAndView deleteUser(@RequestParam Long id) {

        try {

            userService.deleteById(id);

        } catch (Exception e) {
            return new ModelAndView(REDIRECT_ACTION + ADMIN_JSP, RESPONSE, DELETE_USER_FAIL);

        }
        return new ModelAndView(REDIRECT_ACTION + ADMIN_JSP, RESPONSE, DELETE_USER_OK);
    }

    @PostMapping(value = "/сhangeStatus")
    public ModelAndView сhangeStatus(@RequestParam Long userId, @RequestParam String status,
                                     @RequestParam(defaultValue = "0") int duration) {

        if (duration >= 0 && duration <= 180){

            try {

                userService.changeStatus(userId, status, duration);
                return new ModelAndView(REDIRECT_ACTION + ADMIN_JSP, RESPONSE, CHANGE_STATUS_OK);

            } catch (Exception e) {
                return new ModelAndView(REDIRECT_ACTION + ADMIN_JSP, RESPONSE, CHANGE_STATUS_FAIL);

            }
        }
        return new ModelAndView(REDIRECT_ACTION + ADMIN_JSP, RESPONSE, INVALID_DATA);

    }

}
