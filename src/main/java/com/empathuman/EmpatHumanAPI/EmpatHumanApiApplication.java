package com.empathuman.EmpatHumanAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "services"})
public class EmpatHumanApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpatHumanApiApplication.class, args);
	}

}
