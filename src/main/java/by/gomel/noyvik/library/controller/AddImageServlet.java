package by.gomel.noyvik.library.controller;

import by.gomel.noyvik.library.service.BookService;
import by.gomel.noyvik.library.service.provider.ProviderService;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

import static by.gomel.noyvik.library.controller.constant.CommandConstant.BOOK_ID;
import static by.gomel.noyvik.library.controller.constant.CommandConstant.IMAGE;

@WebServlet(name = "AddImageServlet", urlPatterns = {"/addImage"})
@MultipartConfig
public class AddImageServlet extends HttpServlet {

    private ProviderService providerService = ProviderService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {

            BookService bookService = providerService.getBookService();

            long id = Long.parseLong(request.getParameter(BOOK_ID));
            Part part = null;
            part = request.getPart(IMAGE);

            if (part != null) {

                try (InputStream inputStream = part.getInputStream()) {
                   // byte[] image = inputStream.readAllBytes(); todo JaVa version
                  //  bookService.addImage(id, image);
                    response.sendRedirect("/redirect?target=main&resp=Add image completed");

                }

            } else {
                response.sendRedirect("/redirect?target=main&resp=Add image fail");

            }

        } catch (Exception e) {
            response.sendRedirect("/redirect?target=main&resp=Add image fail");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
