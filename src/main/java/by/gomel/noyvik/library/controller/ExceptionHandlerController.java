package by.gomel.noyvik.library.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static by.gomel.noyvik.library.util.constant.ApplicationConstant.*;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    protected ModelAndView handleConflict() {
        return new ModelAndView(ERROR_JSP);
    }
}

