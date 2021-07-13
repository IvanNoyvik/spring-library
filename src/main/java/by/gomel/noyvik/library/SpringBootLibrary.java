package by.gomel.noyvik.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringBootLibrary {


        public static void main(String[] args) {
            SpringApplication.run(SpringBootLibrary.class, args);
        }

}
