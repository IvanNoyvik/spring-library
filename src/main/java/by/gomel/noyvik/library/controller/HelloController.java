package by.gomel.noyvik.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloController {

    @GetMapping("/")
    protected ModelAndView loadHelloPage() {
        return new ModelAndView("hello");
    }
}
