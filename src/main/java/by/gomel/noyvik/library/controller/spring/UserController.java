package by.gomel.noyvik.library.controller.spring;

import by.gomel.noyvik.library.model.Authenticate;
import by.gomel.noyvik.library.model.User;
import by.gomel.noyvik.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.LOGIN_FAIL;
import static by.gomel.noyvik.library.controller.constant.CommandConstant.USER;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
//    private final OrderService orderService;
//    private final MessageService messageService;


    @PostMapping(value = "/login")
    public ModelAndView login(@Valid @ModelAttribute Authenticate authenticate, HttpSession session, BindingResult br) {

        ModelAndView modelAndView = new ModelAndView("redirect:/main");

        if (br.hasErrors()){
            Map<String, Object> model = br.getModel();
            modelAndView.addObject(model);
            System.out.println();
            return modelAndView;

        }
        User user = userService.login(authenticate);

        if (user == null){
            modelAndView.addObject("error", LOGIN_FAIL);
//            modelAndView.setViewName("redirect:/main");
            return modelAndView;
        } else {
            session.setAttribute(USER, user);
        }

        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView registration(@Valid @RequestParam User user , BindingResult br){

        if (br.hasErrors()){

            return new ModelAndView("redirect:/main");

        }

        userService.createNewUser(user);


        return new ModelAndView("redirect:/login");
    }


}
