package by.gomel.noyvik.library.controller;

import by.gomel.noyvik.library.exception.ServiceException;
import by.gomel.noyvik.library.model.Authenticate;
import by.gomel.noyvik.library.model.User;
import by.gomel.noyvik.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static by.gomel.noyvik.library.util.constant.ApplicationConstant.*;

@Controller
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/registration")
    @Validated
    public ModelAndView registration(@Valid @ModelAttribute User user, /*@Valid @ModelAttribute Authenticate authenticate, */BindingResult br) {

        if (br.hasErrors()) {
            return new ModelAndView(REGISTRATION_JSP, "user", user);
        }

        try {

            user.addAuthenticate(user.getAuthenticate());
            userService.createNewUser(user);

        } catch (ServiceException e) {

            return new ModelAndView(REDIRECT_ACTION + PAGE + REGISTRATION_JSP, ANSWER, USER_EXISTS);


        } catch (Exception e) {
            return new ModelAndView(REDIRECT_ACTION + PAGE + REGISTRATION_JSP, ANSWER, REGISTRATION_FAIL);

        }
        return new ModelAndView(REDIRECT_ACTION + MAIN_JSP, ANSWER, REGISTRATION_OK);

    }

    @PostMapping(value = "/login")
    public ModelAndView login(@Valid @ModelAttribute Authenticate authenticate, BindingResult br, HttpSession session) {


        if (br.hasErrors()) {
            return new ModelAndView(LOGIN_JSP, "authenticate", authenticate);
        }


        User user = userService.login(authenticate);

        if (user == null) {

            return new ModelAndView(REDIRECT_ACTION + PAGE + LOGIN_JSP, ANSWER, LOGIN_FAIL);

        } else {
            session.setAttribute(USER, user);
        }

        if (user.getStatus().getStatus().equals(LOCKED)) {

            return new ModelAndView(REDIRECT_ACTION + BLOCK_JSP);
        }

        return new ModelAndView(REDIRECT_ACTION + MAIN_JSP, ANSWER, LOGIN_OK + user.getName());

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
            List<String> errors = br.getFieldErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
            return new ModelAndView(EDIT_USER_JSP, "errors", errors);
        }

        try {

            User userForUpdate = (User) session.getAttribute(USER);
            userForUpdate.setEmail(user.getEmail());
            userForUpdate.setName(user.getName());
            userService.updateUser(userForUpdate);

        } catch (Exception e) {
            return new ModelAndView(REDIRECT_ACTION + PAGE + EDIT_USER_JSP, ANSWER, EDIT_USER_FAIL);

        }
        return new ModelAndView(REDIRECT_ACTION + PROFILE_JSP, ANSWER, EDIT_USER_OK);


    }

    @PostMapping(value = "/deleteUser")
    public ModelAndView deleteUser(@RequestParam Long id) {

        try {

            userService.deleteById(id);

        } catch (Exception e) {
            return new ModelAndView(REDIRECT_ACTION + ADMIN_JSP, ANSWER, DELETE_USER_FAIL);

        }
        return new ModelAndView(REDIRECT_ACTION + ADMIN_JSP, ANSWER, DELETE_USER_OK);
    }

    @PostMapping(value = "/сhangeStatus")
    public ModelAndView сhangeStatus(@RequestParam Long userId, @RequestParam String status,
                                     @RequestParam(defaultValue = "0") int duration) {

        if (duration >= 0 && duration <= 180) {

            try {

                userService.changeStatus(userId, status, duration);
                return new ModelAndView(REDIRECT_ACTION + ADMIN_JSP, ANSWER, CHANGE_STATUS_OK);

            } catch (Exception e) {
                return new ModelAndView(REDIRECT_ACTION + ADMIN_JSP, ANSWER, CHANGE_STATUS_FAIL);

            }
        }
        return new ModelAndView(REDIRECT_ACTION + ADMIN_JSP, ANSWER, INVALID_DATA);

    }

}
