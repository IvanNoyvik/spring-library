package by.gomel.noyvik.library.controller;

import by.gomel.noyvik.library.controller.attribute.AttributeSetterMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.MAIN_JSP;


@Controller
public class HelloController {

    @GetMapping(value = {"/", "/main"})
    protected ModelAndView loadHelloPage(HttpServletRequest request) {

        AttributeSetterMapper.getInstance().mapAndSetAttribute(MAIN_JSP, request);
        return new ModelAndView("main");
    }
}
