package com.local.test.reptile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

}
