package by.gomel.noyvik.library.controller;

import by.gomel.noyvik.library.bean.Role;
import by.gomel.noyvik.library.bean.User;
import by.gomel.noyvik.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static by.gomel.noyvik.library.util.ErrorConstant.*;


@Controller
public class AuthenticateController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    protected ModelAndView loadLoginPage() {
        return new ModelAndView("authenticate");
    }

    @PostMapping("/login")
    public ModelAndView processLogin(@RequestParam String login, @RequestParam String password) {

        try {

            if (login == null || login.isEmpty()) {
                throw new RuntimeException(INVALID_USER_LOGIN);
            }
            if (password == null || password.isEmpty()) {
                throw new RuntimeException(INVALID_USER_PASSWORD);
            }

            if (!userRepository.isUserLoginExist(login)) {
                throw new RuntimeException(INVALID_USER_AUTHENTICATION_DATA);
            }

            User user = userRepository.getByLoginAndPassword(login, password);


            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", user);
            modelAndView.setViewName("main");

            if (user.getRole() == Role.ADMIN) {
                modelAndView.addObject("users", userRepository.getAllUsers());
            }

            return modelAndView;

        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("authenticate");

            return modelAndView;
        }
    }

    @GetMapping("/registration")
    public ModelAndView loadRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView("registration");

        User user = new User();
        user.setRole(Role.USER);

        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @GetMapping("/registration_with_spring_tag")
    public ModelAndView loadRegistrationWhitSpringTagPage() {
        ModelAndView modelAndView = new ModelAndView("registration_with_spring_tag");

        User user = new User();
        user.setRole(Role.USER);

        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView processRegistration(@ModelAttribute User user) {

        try {

            if (user.getLogin() == null || user.getLogin().isEmpty()) {
                throw new RuntimeException(INVALID_USER_LOGIN);
            }
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                throw new RuntimeException(INVALID_USER_PASSWORD);
            }

            if (userRepository.isUserLoginExist(user.getLogin())) {
                throw new RuntimeException(INVALID_USER_REGISTRATION_DATA);
            }


            user = userRepository.create(user);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", user);
            modelAndView.setViewName("main");

            if (user.getRole() == Role.ADMIN) {
                modelAndView.addObject("users", userRepository.getAllUsers());
            }

            return modelAndView;

        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", e.getMessage());
//            modelAndView.setViewName("registration");
            modelAndView.setViewName("registration_with_spring_tag");

            return modelAndView;
        }
    }

}
