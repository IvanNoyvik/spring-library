package by.gomel.noyvik.library.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import({PersistenceConfig.class, ServiceConfig.class})
@PropertySource("classpath:application.properties")
@ComponentScan("by.gomel.noyvik.library.config")
public class ApplicationConfig {
}
