package by.gomel.noyvik.library.config;

import org.springframework.context.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@Import({PersistenceConfig.class, ServiceConfig.class})
@PropertySource("classpath:application.properties")
@ComponentScan("by.gomel.noyvik.library.config")
public class ApplicationConfig {


    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }


}
