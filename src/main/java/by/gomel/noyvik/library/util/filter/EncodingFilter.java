//package by.gomel.noyvik.library.util.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebInitParam;
//import java.io.IOException;
//
//@WebFilter(filterName = "EncodingFilter", initParams = {
//        @WebInitParam(name = "encoding", value = "UTF-8")}, urlPatterns = {"/*"})
//public class EncodingFilter implements Filter {
//
//    private String encoding;
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//        encoding = filterConfig.getInitParameter("encoding");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        String codeRequest = servletRequest.getCharacterEncoding();
//
//        if (encoding != null && !encoding.equalsIgnoreCase(codeRequest)) {
//            servletRequest.setCharacterEncoding(encoding);
//            servletResponse.setCharacterEncoding(encoding);
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//    }
//}
