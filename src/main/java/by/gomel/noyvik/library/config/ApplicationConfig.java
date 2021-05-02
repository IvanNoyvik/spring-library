package by.gomel.noyvik.library.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@Import({PersistenceConfig.class, ServiceConfig.class})
@PropertySource("classpath:application.properties")
@ComponentScan("by.gomel.noyvik.library.config")
public class ApplicationConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("classpath:messages");
        return source;
    }

    @Bean
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;

    }
}
