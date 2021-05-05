package by.gomel.noyvik.library.controller.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.RESPONSE;


@Controller
public class ModelController {

    @Autowired
    protected MessageSource messageSource;


    protected ModelAndView addRespObjectAndViewNameInModelAndView(ModelAndView modelAndView, String viewName, String resp, String jspPage) {
        modelAndView.addObject(RESPONSE, resp);
        viewName += jspPage;
        modelAndView.setViewName(viewName);
        return modelAndView;
    }


    protected ModelAndView setErrorsInModelAndView(BindingResult br, ModelAndView modelAndView, String viewName, String jspPage) {
        List<FieldError> fieldErrors = br.getFieldErrors();
        String resp = "";
        for (ObjectError error : fieldErrors) {

            resp += messageSource.getMessage(error, null) + "\n";
        }

        return addRespObjectAndViewNameInModelAndView(modelAndView, viewName, resp, jspPage);
    }
}
