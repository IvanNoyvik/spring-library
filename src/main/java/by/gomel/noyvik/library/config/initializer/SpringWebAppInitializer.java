package by.gomel.noyvik.library.config.initializer;

import by.gomel.noyvik.library.config.ApplicationConfig;
import by.gomel.noyvik.library.config.WebConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ApplicationConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new CharacterEncodingFilter()};
    }

//    private String TMP_FOLDER = "/tmp";
//    private int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;

//    @Override
//    public void onStartup(ServletContext sc) throws ServletException {
//
//        ServletRegistration.Dynamic appServlet = sc.addServlet("mvc", new DispatcherServlet(
//                new GenericWebApplicationContext()));
//
//        appServlet.setLoadOnStartup(1);
//
//        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER,
//                MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);
//
//        appServlet.setMultipartConfig(multipartConfigElement);
//    }
    //
//
//    private MultipartConfigElement getMultipartConfigElement() {
//        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(file.getAbsolutePath());
//    return multipartConfigElement;
//    }
//
//    @Override
//    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
//        registration.setMultipartConfig(getMultipartConfigElement());
//    }
}