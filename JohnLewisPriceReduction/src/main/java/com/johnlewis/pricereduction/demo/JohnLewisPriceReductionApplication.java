package com.johnlewis.pricereduction.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.johnlewis.pricereduction.*")
public class JohnLewisPriceReductionApplication {

	public static void main(String[] args) {
		SpringApplication.run(JohnLewisPriceReductionApplication.class, args);
	}

}
