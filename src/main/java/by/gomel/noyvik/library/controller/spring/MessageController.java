package by.gomel.noyvik.library.controller.spring;

import by.gomel.noyvik.library.model.Message;
import by.gomel.noyvik.library.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/sentMessage")
    public ModelAndView addAuthor(@Valid @ModelAttribute Message message, BindingResult br) {

        if (br.hasErrors()) {
            List<String> errors = br.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
            return new ModelAndView(BLOCK_JSP, "errors", errors);
        }

        messageService.save(message);
        return new ModelAndView(REDIRECT_ACTION + BLOCK_JSP);

    }

}
