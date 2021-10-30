package dev.mazurkiewicz.quizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class QuizerApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(QuizerApp.class, args);
    }

}
