package by.gomel.noyvik.library.controller.commands;

import by.gomel.noyvik.library.controller.FrontCommand;

import javax.servlet.ServletException;
import java.io.IOException;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.*;

public class ForwardCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {

        String target = request.getParameter(FORWARD);

        forward(target);
    }
}
