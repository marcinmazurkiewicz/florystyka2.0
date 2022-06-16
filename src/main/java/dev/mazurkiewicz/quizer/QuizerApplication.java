package dev.mazurkiewicz.quizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan({"dev.mazurkiewicz.quizer.config"})
public class QuizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizerApplication.class, args);
	}

}
