package by.gomel.noyvik.library.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("by.gomel.noyvik.library.service")
@ComponentScan("by.gomel.noyvik.library.util")
public class ServiceConfig {
}
