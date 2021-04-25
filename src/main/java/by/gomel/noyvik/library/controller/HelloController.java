package by.gomel.noyvik.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class HelloController {

    @GetMapping(value = {"/", "/main"})
    protected String loadHelloPage(HttpServletRequest request) {

//        AttributeSetterMapper.getInstance().mapAndSetAttribute(MAIN_JSP, request);
//        return new ModelAndView("main");
        return ("main");
    }
}
